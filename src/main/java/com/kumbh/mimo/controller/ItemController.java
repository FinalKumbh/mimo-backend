package com.kumbh.mimo.controller;

import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.item.ItemRepository;
import com.kumbh.mimo.domain.user.User;
import com.kumbh.mimo.dto.item.ItemFormDto;
import com.kumbh.mimo.exception.ResourceNotFoundException;
import com.kumbh.mimo.security.CurrentUser;
import com.kumbh.mimo.security.UserPrincipal;
import com.kumbh.mimo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/new")
    public String itemForm(Model model){
        model.addAttribute("itemFormDto", new ItemFormDto());
        return "/item/itemForm";
    }

    @PostMapping(value = "/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList){

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }

        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == 0){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "item/itemForm";
        }

        return "redirect:/item/new";
    }

//    @GetMapping(value = "/{itemId}")
//    public String item(Model model, @PathVariable("itemId") Long itemId){
//        ItemFormDto itemFormDto = itemService.getItem(itemId);
//        model.addAttribute("item", itemFormDto);
//        return "item/item";           //HTML
//    }

//    @GetMapping("/{itemId}")
//    public @ResponseBody ResponseEntity<?> getItem(@PathVariable Long itemId){
//        String result = "";
//
//        try{
//            ItemFormDto itemFormDto = itemService.getItem(itemId);
//            result = "item_id : " + itemId + " Get OK";
//            return new ResponseEntity<ItemFormDto>(itemFormDto, HttpStatus.OK);     //JSON
//        } catch(Exception e){
//            result = "item_id : " + itemId + " Get Fail";
//            return new ResponseEntity<ItemFormDto>(HttpStatus.NO_CONTENT);
//        }
//    }

    @GetMapping("/{itemId}")
    public ResponseEntity<?> item(@PathVariable Long itemId){
        ItemFormDto itemFormDto = itemService.getItem(itemId);

        if(itemFormDto == null){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itemFormDto);      //JSON
    }

    @GetMapping("/view")
    public List<Item> getAllItems(){
        return itemService.getAllItem();
    }

//    @GetMapping("/view")
//    public ResponseEntity<?> getAllItems(){
//        ItemFormDto itemFormDto = itemService.getAllItem();
//        return ResponseEntity.ok(itemFormDto);
//    }

}

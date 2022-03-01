package com.kumbh.mimo.service;

import com.kumbh.mimo.domain.item.Item;
import com.kumbh.mimo.domain.item.ItemImg;
import com.kumbh.mimo.domain.item.ItemImgRepository;
import com.kumbh.mimo.domain.item.ItemRepository;
import com.kumbh.mimo.dto.item.ItemFormDto;
import com.kumbh.mimo.dto.item.ItemImgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemImgService itemImgService;

    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{

        //상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        //이미지 등록
        for(int i=0;i<itemImgFileList.size();i++){
            if (itemImgFileList.get(i).isEmpty()){
            }
            else {
                ItemImg itemImg = new ItemImg();
                itemImg.setItem(item);

                if (i == 0)
                    itemImg.setRepimgYn("Y");
                else
                    itemImg.setRepimgYn("N");

                itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
            }
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItem(Long itemId){
        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<>();
        for (ItemImg itemImg : itemImgList) {
            ItemImgDto itemImgDto = ItemImgDto.of(itemImg);
            itemImgDtoList.add(itemImgDto);
        }

        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setItemImgDtoList(itemImgDtoList);
        return itemFormDto;
    }

    public List<Item> getAllItem(){
        return itemRepository.findAll();
    }




}

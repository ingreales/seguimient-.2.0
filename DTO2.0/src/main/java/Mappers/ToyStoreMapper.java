package Mappers;

import Mapping.ToyDTO;

public class ToyStoreMapper {
    public static model.Toy mapFrom(ToyDTO toyStoreDTO){
        return new model.Toy(toyStoreDTO.name(),toyStoreDTO.type(),toyStoreDTO.price(),toyStoreDTO.amount());
    }

    public static ToyDTO mapFrom(model.Toy toy){return  new ToyDTO(toy.getName(),toy.getType(),toy.getPrice(),toy.getAmount()  );}}

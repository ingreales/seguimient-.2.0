package services;

import Mapping.ToyDTO;
import com.sun.jdi.Type;

import java.util.List;
import java.util.Map;

public interface ToyStoreInt {
    //add, list
    List<ToyDTO> addToy(ToyDTO toyStoreDTO) throws Exception;
    List<ToyDTO> listToys();
//   List<ToyStoreDTO> deleteToys(String name) throws Exception;

    ToyDTO search(String name) throws Exception;
    Map.Entry<Type,Integer> maxToy() throws Exception;
    Map.Entry<Type,Integer> minToy() throws Exception;
    List<ToyDTO> increase(ToyDTO toyStoreDTO, int amount) throws Exception;
    List<ToyDTO> decrease(ToyDTO toyStoreDTO, int amount) throws Exception;
    Map<Type,Integer> showByType() throws Exception;
    Map<Type,Integer> sort() throws Exception;
    List<ToyDTO> showToysAbove(double value) throws Exception;
    Boolean verifyExist(String name);
    Integer totalToys() throws Exception;
    Integer totalPriceAllToys() throws Exception;
//    void  update(ToyStoreDTO toyStoreDTO) throws Exception;
}

package services.implement;

import Mappers.ToyStoreMapper;
import Mapping.ToyDTO;
import Utilis.Constants;
import Utilis.FileUtils;
import com.sun.jdi.Type;
import services.ToyStoreInt;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ToyServiceImpl implements ToyStoreInt {

    private List<model.Toy> toyList;
    public void setToyList(List<model.Toy> toyList) {
        this.toyList = toyList;
    }

    public ToyServiceImpl(){
        toyList = FileUtils.getToys(new File(Constants.PATH_TOYS));
    }
    @Override
    public List<ToyDTO> addToy(ToyDTO toyStoreDTO) throws Exception {
        if(!verifyExist(toyStoreDTO.name())){
            toyList.add(ToyStoreMapper.mapFrom(toyStoreDTO));
            FileUtils.saveToys(new File(Constants.PATH_TOYS), toyList);
            return toyList.stream().map(ToyStoreMapper::mapFrom).toList();
        }
        throw new Exception("This toy is in the list");
    }

    @Override
    public List<ToyDTO> listToys() {
        return toyList.stream().map(ToyStoreMapper::mapFrom).toList();
    }

    @Override
    public ToyDTO search(String name) throws Exception {
        if(verifyExist(name)){
            List<ToyDTO> list = toyList.stream().filter(toyList-> Objects.equals(toyList.getName(), name))
                    .findFirst().stream().map(ToyStoreMapper::mapFrom).toList();
            return list.getFirst();
        }
        throw new Exception("Dont found");
    }

    @Override
    public Map.Entry<Type,Integer> maxToy() throws Exception {
        return sort().entrySet().stream().reduce((first,second)-> second).orElse(null);
    }

    @Override
    public Map.Entry<Type,Integer> minToy() throws Exception {
        return sort().entrySet().stream().findFirst().orElse(null);
    }

    @Override
    public List<ToyDTO> increase(ToyDTO toyStoreDTO, int amount) throws Exception {
        if(verifyExist(toyStoreDTO.name())){
            toyList.stream().filter(toy1 -> Objects.equals(toy1.getName(), toyStoreDTO.name()))
                    .peek(toy -> toy.setAmount(toy.getAmount() + amount))
                    .findFirst();
            FileUtils.saveToys(new File(Constants.PATH_TOYS), toyList);
            return toyList.stream().map(ToyStoreMapper::mapFrom).toList();
        } throw new Exception("The toy is not in the list");
    }
    @Override
    public List<ToyDTO> decrease(ToyDTO toyStoreDTO, int amount) throws Exception {
        toyList.stream().filter(toy1 -> Objects.equals(toy1.getName(),toyStoreDTO.name()))
                .peek(toy -> {
                    if(toy.getAmount()>0){
                        toy.setAmount(toy.getAmount() - amount);
                    } else if (toy.getAmount()<amount) {
                        System.out.println("No puedes eliminar mas unidades que las existentes");
                    } else {
                        toyList.remove(toy);
                    }
                })
                .findFirst();
        FileUtils.saveToys(new File(Constants.PATH_TOYS), toyList);
        return toyList.stream().map(ToyStoreMapper::mapFrom).toList();
    }


    @Override
    public Map<Type, Integer> showByType() throws Exception {
        if(toyList!=null) {
            Map<Type, Integer> showByType = new TreeMap<>();
            for (model.Toy toy : toyList) {
                Type type = toy.getType();
                showByType.put(type, showByType.getOrDefault(type, 0) + 1);
            }
            return showByType;
        } throw new Exception("The list is null");
    }

    @Override
    public Map<Type, Integer> sort() throws Exception {
        if(!toyList.isEmpty()) {
            return showByType().entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue,
                            LinkedHashMap::new));
        } throw new Exception("The list is empty");
    }

    @Override
    public List<ToyDTO> showToysAbove(double value) throws Exception {
        if(!toyList.isEmpty()) {
            List<ToyDTO> storeDTOList = toyList.stream()
                    .filter(toy -> toy.getPrice() >= value)
                    .toList().stream().map(ToyStoreMapper::mapFrom).toList();
            if (storeDTOList.isEmpty()) {
                System.out.println("There is not toys above that price");
            }
            return storeDTOList;
        } throw new Exception("The list is empty");
    }
    @Override
    public Boolean verifyExist(String name) {
        return toyList.stream().anyMatch(e -> e.getName().equalsIgnoreCase(name));
    }
    @Override
    public Integer totalToys() throws Exception {
        Integer totalCount = 0;
        for (model.Toy toy : toyList){
            totalCount += toy.getAmount();
        }
        return totalCount;
    }

    @Override
    public Integer totalPriceAllToys() throws Exception {
        Integer totalPrice = 0;
        for (model.Toy toy:toyList){
            totalPrice += toy.getPrice();
        }
        return totalPrice;
    }



}

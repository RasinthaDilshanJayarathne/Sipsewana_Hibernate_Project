package dao.custom;

import dto.CustomDTO;

import java.util.ArrayList;
import java.util.List;

public interface QueryDAO extends SuperDAO{
    ArrayList<CustomDTO> getAll();
    List<CustomDTO> searchDetail(String s);
}

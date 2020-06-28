package model.services;

import java.util.List;

public interface Service {

    String[][] getProcessedData(List<List<String>> schedule);
}

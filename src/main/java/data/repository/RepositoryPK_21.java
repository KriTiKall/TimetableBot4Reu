package data.repository;

import data.Parser;
import data.ParserPK_21;
import model.mappers.Mapper4Array;
import model.services.ScheduleService;
import model.services.Service;

import java.util.List;

public class RepositoryPK_21 implements Repository<String[][]>{

    private Service service;
    private Parser parser;

    public RepositoryPK_21() {
        this.service = new ScheduleService(new Mapper4Array());
        this.parser = new ParserPK_21();
    }

    @Override
    public String[][] getSchedule() {
        return service.getProcessedData(parser.parse());
    }
}

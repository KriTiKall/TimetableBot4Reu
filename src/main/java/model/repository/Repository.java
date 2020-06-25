package model.repository;

import data.Parser;
import model.mappers.Mapper;

import java.util.List;
import java.util.function.UnaryOperator;

public class Repository {

    private Parser parser;
    private Mapper<List<List<String>>, String[][]> mapper;
    private UnaryOperator<String[][]> processing;

    public Repository(Parser parser, Mapper mapper, UnaryOperator processing) {
        this.parser = parser;
        this.mapper = mapper;
        this.processing = processing;
    }

    public String[][] getSchedulePK_21(){
        return processing.apply(mapper.map(parser.parse()));
    }
}

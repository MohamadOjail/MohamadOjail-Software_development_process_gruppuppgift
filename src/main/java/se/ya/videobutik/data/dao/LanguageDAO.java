package se.ya.videobutik.data.dao;

import se.ya.videobutik.data.DataManagement;
import se.ya.videobutik.model.Language;

import java.util.ArrayList;
import java.util.Collection;

public class LanguageDAO {
    public void AddLanguage(Language language){
        DataManagement data = new DataManagement();
        data.setData(language);
    }
    public Language findLanguage(int id){
        DataManagement data = new DataManagement();
        return (Language) data.getData(Language.class, id);
    }
    public void deleteLanguage(Language language){
        DataManagement data = new DataManagement();
        data.removeData(language);
    }
    public void updateLanguage(Language language){
        DataManagement data = new DataManagement();
        data.updateData(language);
    }
    public Collection<Language> getLanguageList(){

        Collection<Language> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Language.class);
        for (Object x : dataList){
            output.add((Language) x);
        }
        return output;
    }

    public Collection<Language> getLanguageList(String text){

        Collection<Language> output = new ArrayList<>();
        DataManagement data = new DataManagement();
        Collection<Object> dataList = data.getDataList(Language.class, text);
        for (Object x : dataList){
            output.add((Language) x);
        }
        return output;
    }
}


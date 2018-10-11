package idwall.iddog.ui.dogs;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import idwall.iddog.data.DogsRepository;

public class DogsViewModel extends ViewModel {

    private DogsRepository dogsRepository;

    public void init(Context context) {
        dogsRepository = new DogsRepository(context);
    }

    public MutableLiveData<List<String>> feed(String breed) {

        return dogsRepository.getDogs(breed);

    }

}

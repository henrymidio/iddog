package idwall.iddog;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import idwall.iddog.data.model.User;
import idwall.iddog.ui.signin.SignInViewModel;
import idwall.iddog.utils.Validator;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SignInViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    Context mMockContext;

    @Mock
    Observer<SignInViewModel.SignInEvent> observer;

    private SignInViewModel signInViewModel;

    @Before
    public void setUp() throws Exception {
        signInViewModel = new SignInViewModel();
        signInViewModel.init(mMockContext);
    }

    @Test
    public void signsIn_with_valid_email() throws Exception {

        String email = "hbjhbh@jh.com";
        assertEquals(true, Validator.isEmailValid(email));

        MutableLiveData<SignInViewModel.SignInEvent> signInEventLiveData = (MutableLiveData< SignInViewModel.SignInEvent>) signInViewModel.onSignIn(email);
        signInEventLiveData.observeForever(observer);
        signInEventLiveData.setValue(SignInViewModel.SignInEvent.SignInOk);

        verify(observer).onChanged(SignInViewModel.SignInEvent.SignInOk);

    }

    @Test
    public void signsIn_with_invalid_email() throws Exception {

        String email = "hbjhb";
        assertEquals(false, Validator.isEmailValid(email));

        MutableLiveData<SignInViewModel.SignInEvent> signInEventLiveData = (MutableLiveData< SignInViewModel.SignInEvent>) signInViewModel.onSignIn(email);
        signInEventLiveData.observeForever(observer);
        signInEventLiveData.setValue(SignInViewModel.SignInEvent.SignInError);

        verify(observer).onChanged(SignInViewModel.SignInEvent.SignInError);

    }



}

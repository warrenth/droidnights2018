package com.droidknights.droidtest.step6;

import com.droidknights.droidtest.Calculator;
import com.droidknights.droidtest.ViewModel;
import com.droidknights.droidtest.step4.Step4CalculatorImpl;
import com.jakewharton.rxrelay2.PublishRelay;
import com.jakewharton.rxrelay2.Relay;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Module
public class Step6UnitTestModule {
    @Provides @Singleton
    public Relay<Response<String>> provideHttpStream() {
        return PublishRelay.create();
    }

    @Provides @Singleton
    public Calculator provideCalculator() {
        /**
         * https://github.com/mockito/mockito/wiki/Mockito-features-in-Korean
         *  Mockito는 자바에서 단위테스트를 하기 위해 Mock을 만들어주는 프레임워크입니다.
            Mock이 필요한 테스트에 직관적으로 사용할 수 있도록 만들어졌습니다.
         *
         */

        Calculator calculator = mock(Calculator.class);

        //Calculator calculator = new Step4CalculatorImpl();

        when(calculator.calculate("1+1")).thenReturn(Single.just(Response.success("2")));
        when(calculator.calculate("1-1")).thenReturn(Single.just(Response.success("0")));
        when(calculator.calculate("3*2")).thenReturn(Single.just(Response.success("9")));
        when(calculator.calculate("8/2")).thenReturn(Single.just(Response.success("4")));

        return calculator;
    }

    @Provides @Singleton
    public ViewModel provideViewModel(Calculator calculator, Relay<Response<String>> httpStream) {
        return new Step6ViewModel(calculator, httpStream);
    }
}

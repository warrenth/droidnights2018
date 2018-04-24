package com.droidknights.droidtest.step4;

import com.droidknights.droidtest.BuildConfig;
import com.droidknights.droidtest.Calculator;
import com.droidknights.droidtest.CalculatorService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Step4CalculatorImpl implements Calculator {


    /**
     *  http://reactivex.io/documentation/ko/single.html
     *  비동기 처리 후 결과만을 반환해야 하는 경우 적절하다.
     *
     *   Observable을 구독할 때 사용하는 세 개의 메서드(onNext, onError, 그리고 onCompleted) 대신 다음의 두 메서드만 사용할 수 있다:
     *   onSuccess
         Single은 자신이 배출하는 하나의 값을 이 메서드를 통해 전달한다
         onError
         Single은 항목을 배출할 수 없을 때 이 메서드를 통해 Throwable 객체를 전달한다
         Single은 이 메서드 중 하나만 그리고, 한 번만 호출한다. 메서드가 호출되면 Single의 생명주기는 끝나고 구독도 종료된다.
     *
     *
     */
    void sampleCode() {

    }


    @Override
    public Single<Response<String>> calculate(String expression) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CalculatorService service = retrofit.create(CalculatorService.class);
        return service.calculate(expression);
    }
}
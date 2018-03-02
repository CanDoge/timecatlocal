package com.time.cat.NetworkSystem.api;


import com.microsoft.projectoxford.vision.contract.OCR;

import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by wangyan-pd on 2016/11/17.
 */

public interface MicSoftOcrService {
    @Headers({"User-Agent: Mozilla/5.0", "ocp-apim-subscription-key : 56c87e179c084cfaae9b70a2f58fa8d3"})
    @POST("vision/v1.0/ocr?language=unk&detectOrientation=true")
    Observable<OCR> uploadImage4recognize(@Body String imgs);

}

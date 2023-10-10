package com.realllapp.jobber.retrofit

import com.realllapp.jobber.retrofit.dataclasses.CreateUserResponse
import com.realllapp.jobber.retrofit.dataclasses.User
import com.realllapp.jobber.retrofit.dataclasses.WorkByLocation
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface JobberApi {

    @FormUrlEncoded
    @POST(RetrofitUrls.ENTER_URL + "registration.php")
    suspend fun performUserRegistration(@Field("name") userName : String,
                                        @Field("email") userEmail : String,
                                        @Field("password") userPassword : String,
                                        @Field("birthday") userBirthday:String,
                                        @Field("gender") userGender : String
                                        ): CreateUserResponse


    @FormUrlEncoded
    @POST(RetrofitUrls.ENTER_URL + "login.php")
    suspend fun performUserLogin(@Field("email") userEmail : String,
                                 @Field("password") userPassword : String): User

    @FormUrlEncoded
    @POST(RetrofitUrls.WORK_URL + "get_work_by_location.php")
    suspend fun findWorkByLocation(@Field("latitude") userLatitude : Float,
                                 @Field("longitude") userLongitude : Float,
                                 @Field("distance") userDistance : Int = 10000): WorkByLocation

}
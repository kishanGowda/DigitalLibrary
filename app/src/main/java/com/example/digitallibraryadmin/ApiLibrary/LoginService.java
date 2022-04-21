package com.example.digitallibraryadmin.ApiLibrary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginService {

    String token="Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MTEzMywicGhvbmUiOiIrOTE5ODEyMTY4NTk5IiwidXJsIjoidGVzdC50aGVjbGFzc3Jvb20uYml6Iiwib3JnSWQiOiI0Y2IyNTA5ZC03MGY1LTQzNWUtODc5Mi1kMjQ5Mzc3NDNiNTMiLCJicm93c2VyTG9naW5Db2RlIjoiKzkxOTgxMjE2ODU5OTExMzM2ZTg1ZGVmNi0xMzc0LTRkMTUtYjIyMS1jMzIwZTQ2NTVhNjciLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY1MDI3NjM2MH0.ddd-8Zk1fI8DGIyvhveeRLn6TsY2cD-ssg56m6EK8U4";
    String link="orgurl:test.theclassroom.biz";


    @Headers({token,link})
    @GET("admin-library/class")
     Call<DashBoardOne> getHomepageCall();

    //get standard by id get

    @Headers({token,link})
    @GET("admin-library/standardById")
    Call<StandardByID> standardCall(@Query("id")int id);
    //add teacher


    @Headers({token,link})
    @GET("admin-library/teacher-management")
    Call<List<AddTeacherResponse>> addTeacherCall(@Query("subjectId") int subjectId,@Query("standardId") int standardId);



    //getLibrary
    @Headers({token,link})
    @GET("admin-library/library-contents")
    Call<GetLibraryResponse> getLibraryCall(@Query("topicId")int topicId,@Query("standardId")int standardId,@Query("chapterId")int chapterId);
//
//
    //getLibray_notes
@Headers({token,link})
@GET("admin-library/library-contents")
Call<GetLibraryResponse> getLibraryCall_notes(@Query("topicId")int topicId,@Query("standardId")int standardId,@Query("chapterId")int chapterId,@Query("type")String type);




//UpdateLibraryContent
    @Headers({token,link})
    @POST("admin-library/content")
    Call<UpdateLibraryContentResponse> updateLibraryContentRequestCall(@Body UpdateLibraryContentRequest updateLibraryContentRequest);
//
//  //StandardByid
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital"})
//    @GET("admin-library/standardById")
//    Call<StandardByIdResponse> standardIdCall(@Query("id") int id);
//
//    //TopicFilterTopicAdmin
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital","version:7"})
//    @GET("admin-library/filterTopic")
//    Call<List<TopicFiterTAResponse>> topicFiltercall(@Query("subjectId")int subjectId, @Query("standardId")int standardId, @Query("chapterId")int chapterId);
//
////chapterList

    @Headers({token,link})
    @GET("admin-library/chapter-list")
    Call<ChapterListResponse> chapterListCall(@Query("subjectId")int subjectId,@Query("standardId")int standardId);

    //postTeacherMngt

    @Headers({token,link})
    @POST("admin-library/teacher-management")
    Call<PostUpdateResponse> postTeachermngtCall(@Body AddTeacherRequestPost postTeacherManagementRequest);

    //get subject filter
    @Headers({token,link})
    @GET("admin-library/filterSubject")
    Call<List<SubjectFilterResponse>> subjectFilterCall();

////getTopic

@Headers({token,link})
@GET("admin-library/topic-list-by-chapterId")
Call<List<TopicResponse>> getTopicCall(@Query("chapterId")int chapterId, @Query("subjectId")int subjectId, @Query("standardId")int standardId);

//editTopic

    @Headers({token,link})
    @POST("admin-library/topic")
    Call<EditTopicResponse> editTopicCall(@Body EditTopicRequest editTopicRequest);

//delete topic

    @Headers({token,link})
    @POST("admin-library/topic")
    Call<DeleteTopicResponse> deleteCall(@Body DeleteTopicRequest deleteTopicRequest);

//getStandardBySubjectId

    @Headers({token,link})
    @GET("admin-library/standardBySubject")
    Call<List<GetSubjectBySubIdResponse>> getSubjectByCall( @Query("subjectId")int subjectId);

    //filterchapterinsideedit

    @Headers({token,link})
    @GET("admin-library/filterChapter")
    Call<List<ChapterfilterResponse>> getchapterByCall(@Query("subjectId")int subjectId, @Query("standardId")int standardId);

    ////Filtertopic
    @Headers({token,link})
    @GET("admin-library/filterTopic")
    Call<List<TopicFilterResponse>> filterTopicResponseCall( @Query("subjectId")int subjectId,@Query("standardId")int standardId,@Query("chapterId")int chapterId);

    //delete

    @Headers({token,link})
    @DELETE("admin-library/content/delete")
    Call<DeleteResponse>deleteLibraryTopic(@Query("id")int id);


////createupdateedit
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital","version:7"})
//    @POST("admin-library/topic")
//    Call<CreateUpdateEditResponse> createUpdateEditCall(@Body CreateUpdateEditRequest createUpdateEditRequest);
//
////getLibrary
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital"})
//    @GET("admin-library/library-contents")
//    Call<GetLibraryResponse> getLibraryCall(@Query("topicId")int topicId,@Query("standardId")int standardId,@Query("chapterId")int chapterId);
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital"})
//    @GET("admin-library/library-contents")
//    Call<GetLibraryCopyResponse> getLibraryCopyCall(@Query("topicId")int topicId,@Query("standardId")int standardId,@Query("chapterId")int chapterId);
//
////getStandardBySubjectId
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital","version:7"})
//    @GET("admin-library/standardBySubject")
//    Call<List<GetSubjectBySubIdResponse>> getSubjectByCall( @Query("subjectId")int subjectId);
////Filtertopic
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital","version:7"})
//    @GET("admin-library/filterTopic")
//    Call<List<FilterTopicResponse>> filterTopicResponseCall( @Query("subjectId")int subjectId,@Query("standardId")int standardId,@Query("chapterId")int chapterId);
//
//    @Headers({"Authorization:Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NDI0LCJwaG9uZSI6IjIwMzk0ODg5NTIiLCJ1cmwiOiJicmlnb3NoYS5jbGFzc3Jvb20uZGlnaXRhbCIsIm9yZ0lkIjoiZjg2MDg0OTctZmIzNC00YWJkLWE4YWQtM2ZiN2VkNzNmNTFkIiwiYnJvd3NlckxvZ2luQ29kZSI6IjIwMzk0ODg5NTI0MjQ2M2FlNGYwMi1iZWM4LTRkNzMtYTNlNC0zNjhiYjg4NzI1ZDgiLCJkZXZpY2VMb2dpbkNvZGUiOm51bGwsImlhdCI6MTY0MjczODc1N30.NnwUZXQp1bwDsoBJfF_uSGo_pIy-GRgaE8nLhe4kD8I",
//            "orgurl:brigosha.classroom.digital","version:7"})
//    @POST("admin-library/clone-content")
//    Call<CloneLibraryResponse> cloneCall(@Body CloneLibraryRequest cloneLibraryRequest);


}

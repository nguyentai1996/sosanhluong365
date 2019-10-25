package com.example.timviec365.config;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.timviec365.model.Career;
import com.example.timviec365.model.City;
import com.example.timviec365.util.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConfigList extends AsyncTask<Void, Void, Void> {
    private Activity mActivity;
    private String dataFromJson;
//    private ConfigJson configJson;
    private ArrayList<String> listTimeWork;
    private List<String> listSalary;
    private List<String> listRank;
    private List<String> listExp;
    private List<String> listLevel;
    private List<String> listScale;
    private List<String> listScaleCompany;
    private List<String> listExpEmployee;
    private List<String> listTypeCertificate;
    private List<String> listLanguage;
    private List<String> listCertificate;
    private List<String> listOld;
    private List<String> listLanguageDetailEmployee;

    public ConfigList(Activity activity) {
        mActivity = activity;
//        configJson = new ConfigJson();
    }

    private String loadJsonFromAssets() {
        String json = null;
        try {
            InputStream inputStream = mActivity.getAssets().open("sql_cache.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    private String loadJsonJobFromAssets() {
        String json = null;
        try {
            InputStream inputStream = mActivity.getAssets().open("uv_search.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    //load danh sach nganh nghe tu file json
    private String loadJsonCareerFromAssets() {
        String json = null;
        try {
            InputStream inputStream = mActivity.getAssets().open("nganhnghe.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    // lay danh sach thanh pho
    public List<City> getListCity() {
        List<City> cityList = new ArrayList<>();
        JSONObject jsonObject = null;
        JSONObject jsonObject1 = null;
        try {
            jsonObject = new JSONObject(dataFromJson);
            jsonObject1 = jsonObject.getJSONObject("db_city");
            int count = 1;
            try {
                for (int i = 0; i < 64; i++) {
                    JSONObject jsonObject2 = jsonObject1.getJSONObject(String.valueOf(count + i));
                    City city = new City();
                    city.setIdCity(jsonObject2.optString("cit_id"));
                    city.setNameCity(jsonObject2.optString("cit_name"));
                    cityList.add(city);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return cityList;
    }

    // lay danh sach tu khoa viec lam
    public List<String> getListJobKey() {
        List<String> listJobKey = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(loadJsonJobFromAssets());
            for (int i = 0; i < jsonArray.length(); i++) {
                listJobKey.add(jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listJobKey;
    }

    //lay danh sach nganh nghe
    public List<String> getListNganhNghe() {
        List<String> listCarrer = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(loadJsonCareerFromAssets());
            for (int i = 0; i < jsonArray.length(); i++) {
                listCarrer.add(jsonArray.get(i).toString());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listCarrer;
    }

    public void getListDbOptions() {
        listTimeWork = new ArrayList<>();
        listLevel = new ArrayList<>();
        listRank = new ArrayList<>();
        listExp = new ArrayList<>();
        listSalary = new ArrayList<>();
        listScale = new ArrayList<>();
        listScaleCompany = new ArrayList<>();
        listExpEmployee = new ArrayList<>();
        listTypeCertificate = new ArrayList<>();
        listLanguage = new ArrayList<>();
        listCertificate = new ArrayList<>();
        listOld = new ArrayList<>();
        listLanguageDetailEmployee = new ArrayList<>();
        JSONObject jsonObject = null;
        JSONObject jsonObject1 = null;
        try {
            jsonObject = new JSONObject(dataFromJson);
            jsonObject1 = jsonObject.getJSONObject("db_options");
            getListTimeWork(jsonObject1);
            getListSalary(jsonObject1);
            getListRank(jsonObject1);
            getListExp(jsonObject1);
            getListLevel(jsonObject1);
            getListScale(jsonObject1);
            getListScaleCompany(jsonObject1);
            getListExpEmployee(jsonObject1);
            getListTypeCertificate(jsonObject1);
            getListLangguage(jsonObject1);
            getListCertificate(jsonObject1);
            getListOld(jsonObject1);
            getListLangguageEmployeeDetail(jsonObject1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListLangguageEmployeeDetail(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_ngon_ngu");
            for (int i = 0; i < jsonArray.length(); i++) {
                listLanguageDetailEmployee.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListCertificate(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject = jsonObject1.getJSONObject("array_hoc_van");
            for (int i = 0; i < 20; i++) {
                listCertificate.add(jsonObject.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListLangguage(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject = jsonObject1.getJSONObject("array_ngoai_ngu");
            for (int i = 1; i < 9; i++) {
                listLanguage.add(jsonObject.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListTypeCertificate(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_xl");
            for (int i = 0; i < jsonArray.length(); i++) {
                listTypeCertificate.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListTimeWork(JSONObject jsonObject1) {
        int count = 1;
        try {
            JSONObject jsonObject2 = jsonObject1.getJSONObject("array_hinh_thuc");
            for (int i = 0; i < 6; i++) {
                listTimeWork.add(jsonObject2.optString(String.valueOf(count + i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // lay danh sach nghanh nghe
    public ArrayList<Career> getListCareer() {
        ArrayList<Career> lisCareer = new ArrayList<>();
        JSONObject jsonObject = null;
        JSONObject jsonObject1 = null;
        try {
            jsonObject = new JSONObject(dataFromJson);
            JSONArray jsonArray = jsonObject.getJSONArray("db_cat");
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    jsonObject1 = (JSONObject) jsonArray.get(i);
                    Career career = new Career();
                    career.setIdCat(jsonObject1.optString("id"));
                    career.setNameCat(jsonObject1.optString("name"));
                    lisCareer.add(career);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return lisCareer;
    }

    public void getListSalary(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_muc_luong");
            for (int i = 0; i < jsonArray.length(); i++) {
                listSalary.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListOld(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_do_tuoi");
            for (int i = 0; i < jsonArray.length(); i++) {
                listOld.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListRank(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject2 = jsonObject1.getJSONObject("array_capbac");
            for (int i = 0; i < 6; i++) {
                listRank.add(jsonObject2.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListScale(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject2 = jsonObject1.getJSONObject("array_quy_mo");
            for (int i = 0; i < 7; i++) {
                listScale.add(jsonObject2.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListScaleCompany(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject2 = jsonObject1.getJSONObject("array_quy_mo_com");
            for (int i = 1; i < 8; i++) {
                listScaleCompany.add(jsonObject2.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListLevel(JSONObject jsonObject1) {
        try {
            JSONObject jsonObject2 = jsonObject1.getJSONObject("array_hoc_van");
            for (int i = 0; i < 20; i++) {
                listLevel.add(jsonObject2.optString(String.valueOf(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getListExp(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_kinh_nghiem");
            for (int i = 0; i < jsonArray.length(); i++) {
                listExp.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void getListExpEmployee(JSONObject jsonObject1) {
        try {
            JSONArray jsonArray = jsonObject1.getJSONArray("array_kinh_nghiem_uv");
            for (int i = 0; i < jsonArray.length(); i++) {
                listExpEmployee.add((String) jsonArray.get(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dataFromJson = loadJsonFromAssets();
        // lay ten tat ca cong ty :
        List<City> cityList = new ArrayList<>();
        List<String> arrCity = new ArrayList<>();
        cityList.addAll(getListCity());
        for (int i = 0; i < cityList.size(); i++) {
            arrCity.add(cityList.get(i).getNameCity());
        }
        getListDbOptions();
//               saveListSalary();
//        saveListCity((ArrayList<String>) arrCity);
//        saveListTimeWork();
//        saveListRank();
//        saveListExpRecruit();
//        saveListLevel();
//        saveListScale();
//        saveListScaleCompany();
//        saveListExpEmployee();
//        saveListTypeCertificate();
//        saveLanguage();
//        saveListCertificate();
//        saveListOld();
//        saveListLanguageDetail();
//        saveListJobKey((ArrayList<String>) getListJobKey());
//        saveListNganhNghe((ArrayList<String>) getListNganhNghe());

        // lay ten tat ca nganh nghe :
        ArrayList<Career> lisCareer = new ArrayList<>();
        ArrayList<String> lisStringCareer = new ArrayList<>();
        lisCareer.addAll(getListCareer());
        for (int i = 0; i < lisCareer.size(); i++) {
            lisStringCareer.add(lisCareer.get(i).getNameCat());
        }
//        saveListCareer(lisStringCareer);
//        saveListCareerObject(lisCareer);
        HashMap mapCareer = new HashMap();
        for (Career career :
                lisCareer) {
            mapCareer.put(career.getIdCat(), career.getNameCat());
        }
//        saveListCareerMap(mapCareer);
        HashMap mapCity = new HashMap();
        for (City city :
                cityList) {
            mapCity.put(city.getIdCity(), city.getNameCity());
        }
//        saveListCityMap(mapCity);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

//    private void saveListSalary() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_SALARY, configJson.setToString((ArrayList<String>) listSalary));
//    }
//
//    private void saveListCity(ArrayList<String> city) {
//        SharedPrefs.getInstance().put(Constant.LIST_CITY, configJson.setToString(city));
//    }
//
//    private void saveListJobKey(ArrayList<String> listJobKey) {
//        SharedPrefs.getInstance().put(Constant.LIST_JOB_KEY, configJson.setToString(listJobKey));
//    }
//
//    private void saveListNganhNghe(ArrayList<String> listNganhNghe) {
//        SharedPrefs.getInstance().put(Constant.LIST_NGANH_NGHE, configJson.setToString(listNganhNghe));
//    }
//
//    private void saveListTimeWork() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_TIME_WORK, configJson.setToString(listTimeWork));
//    }
//
//    private void saveListOld() {
//        SharedPrefs.getInstance().put(Constant.LIST_OLD, configJson.setToString(
//                (ArrayList<String>) listOld));
//    }
//
//    private void saveListCareer(ArrayList<String> lisCareer) {
//        SharedPrefs.getInstance().put(Constant.LIST_CAREER, configJson.setToString(lisCareer));
//    }
//
//    private void saveListCareerObject(ArrayList<Career> lisCareer) {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_CAREER_OBJECT, configJson.setListCareer(lisCareer));
//    }
//
//    private void saveListCareerMap(HashMap lisCareer) {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_CAREER_MAP, configJson.setListCareerMap(lisCareer));
//    }
//
//    private void saveListCityMap(HashMap mapCity) {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_CITY_MAP, configJson.setListCareerMap(mapCity));
//    }
//
//    private void saveListRank() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_RANK, configJson.setToString((ArrayList<String>) listRank));
//    }
//
//    private void saveListExpRecruit() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_EXP_RECRUIT,
//                        configJson.setToString((ArrayList<String>) listExp));
//    }
//
//    private void saveListLevel() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_LEVEL, configJson.setToString((ArrayList<String>) listLevel));
//    }
//
//    private void saveListScale() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_SCALE, configJson.setToString((ArrayList<String>) listScale));
//    }
//
//    private void saveListScaleCompany() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_SCALE_COMPANY, configJson.setToString((ArrayList<String>) listScaleCompany));
//    }
//
//    private void saveListExpEmployee() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_EXPERIENCE_EMPLOYEE,
//                        configJson.setToString((ArrayList<String>) listExpEmployee));
//    }
//
//    private void saveListTypeCertificate() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_TYPE_CERTIFICATE,
//                        configJson.setToString((ArrayList<String>) listTypeCertificate));
//    }
//
//    private void saveLanguage() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_LANGUAGE,
//                        configJson.setToString((ArrayList<String>) listLanguage));
//    }
//
//    private void saveListCertificate() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_CERTIFICATE,
//                        configJson.setToString((ArrayList<String>) listCertificate));
//    }
//
//    private void saveListLanguageDetail() {
//        SharedPrefs.getInstance()
//                .put(Constant.LIST_LANGUAGE_EMPLOYEE_DETAIL,
//                        configJson.setToString((ArrayList<String>) listLanguageDetailEmployee));
//    }
//    //    @Override
//    //    protected void onPostExecute(ArrayList strings) {
//    //        super.onPostExecute(strings);
//    //        Toast.makeText(mActivity, "Finish", Toast.LENGTH_SHORT).show();
//    //    }
}
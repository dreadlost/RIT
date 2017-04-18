package org.uit.rit.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.uit.rit.EntityGson;
import org.uit.rit.entity.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/ab")
@Produces("application/json; charset=UTF-8")
public class RitApi {

    static final EntityGson<List<Active>> a = new EntityGson<>();
    static final EntityGson<String> stringJson = new EntityGson<>();
    private static List<Active> activeMap = new ArrayList<>();
    private static Logger log = LogManager.getLogger(RitApi.class);

    @GET
    @Path("/addActives")
    @Produces("application/json; charset=UTF-8")
    public Response addActives() {
        Cash cash = new Cash(5000, "Евро", "Талоны на бензин");
        Bank bank = new Bank(3000, "Долларов", "ВТБ24", 55);
        Monetary monetary1 = new Monetary(1, "Деньги в кассе", cash);
        Monetary monetary = new Monetary(2, "Счет в банке", bank);
        Map<String, Object> map = new HashMap<>();
        map.put("улица", "Труда");
        map.put("дом", "23");
        map.put("квартира", "1");
        map.put("комнат", "5");
        NonMonetary nonMonetary = new NonMonetary(3, "Квартира", 2000, 5000, 6000, map);
        activeMap.add(monetary);
        activeMap.add(monetary1);
        activeMap.add(nonMonetary);
        return Response.ok(stringJson.toJson("Добавлен тестовый набор")).build();
    }

    @GET
    @Path("/getActives")
    @Produces("application/json; charset=UTF-8")
    public Response getActives() {
        return Response.ok(a.toJson(activeMap)).build();
    }

    @GET
    @Path("/removeActive")
    @Produces("application/json; charset=UTF-8")
    public Response removeActive(@QueryParam("id") int id) {
        try {
            for (Active active : activeMap) {
                if (active.getId() == id) {
                    activeMap.remove(active);
                }
            }
            return Response.ok(stringJson.toJson("Актив удален успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка удаления актива", e);
            return Response.ok(stringJson.toJson("Произошла ошибка при удалении актива")).build();
        }
    }

    @GET
    @Path("/addMonetaryBank")
    public Response addMonetaryBank(int id, String name, int summ, String currencyName, String nameBank, int numberAcc) {
        try {
            Bank bank = new Bank(summ, currencyName, nameBank, numberAcc);
            Monetary monetary = new Monetary(id, name, bank);
            activeMap.add(monetary);
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления денежного актива", e);
            return Response.ok(stringJson.toJson("Произошла ошибка добавдения денежного актива")).build();
        }
    }

    @GET
    @Path("/addMonetaryCash")
    public Response addMonetaryCash(int id, String name, int summ, String currencyName, String nameCash, int numberAcc) {
        try {
            Cash cash = new Cash(summ, currencyName, nameCash);
            Monetary monetary = new Monetary(id, name, cash);
            activeMap.add(monetary);
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления денежного актива", e);
            return Response.ok(stringJson.toJson("Произошла ошибка добавдения денежного актива")).build();
        }
    }

    @GET
    @Path("/addNonMonetary")
    public Response addNonMonetary(int id, String name, int primary, int residual, int valuation, String measurement) {
        try {
            Map<String, Object> map;
            JSONObject jsonObj = new JSONObject(measurement);
            map = jsonObj.toMap();
            NonMonetary nonMonetary = new NonMonetary(id, name, primary, residual, valuation, map);
            activeMap.add(nonMonetary);
            return Response.ok(stringJson.toJson("НеДенежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления Неденежного актива", e);
            return Response.ok(stringJson.toJson("Произошла ошибка добавдения Неденежного актива")).build();
        }
    }

}

package org.uit.rit.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.uit.rit.EntityGson;
import org.uit.rit.entity.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/rit")
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
            activeMap.removeAll(activeMap);
            return Response.ok(stringJson.toJson("Актив удален успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка удаления актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/addMonetaryBank")
    @Produces("application/json; charset=UTF-8")
    public Response addMonetaryBank(@QueryParam("id") int id,
                                    @QueryParam("name") String name,
                                    @QueryParam("summ") int summ,
                                    @QueryParam("currencyName") String currencyName,
                                    @QueryParam("nameBank") String nameBank,
                                    @QueryParam("numberAcc") int numberAcc) {
        try {
            Bank bank = new Bank(summ, currencyName, nameBank, numberAcc);
            Monetary monetary = new Monetary(id, name, bank);
            activeMap.add(monetary);
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления денежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/addMonetaryCash")
    @Produces("application/json; charset=UTF-8")
    public Response addMonetaryCash(@QueryParam("id") int id,
                                    @QueryParam("name") String name,
                                    @QueryParam("summ") int summ,
                                    @QueryParam("currencyName") String currencyName,
                                    @QueryParam("nameCash") String nameCash,
                                    @QueryParam("numberAcc") int numberAcc) {
        try {
            Cash cash = new Cash(summ, currencyName, nameCash);
            Monetary monetary = new Monetary(id, name, cash);
            activeMap.add(monetary);
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления денежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/addNonMonetary")
    @Produces("application/json; charset=UTF-8")
    public Response addNonMonetary(@QueryParam("id") int id,
                                   @QueryParam("name") String name,
                                   @QueryParam("primary") int primary,
                                   @QueryParam("residual") int residual,
                                   @QueryParam("valuation") int valuation,
                                   @QueryParam("measurement") String measurement) {
        try {
            Map<String, Object> map;
            JSONObject jsonObj = new JSONObject(measurement);
            map = jsonObj.toMap();
            NonMonetary nonMonetary = new NonMonetary(id, name, primary, residual, valuation, map);
            activeMap.add(nonMonetary);
            return Response.ok(stringJson.toJson("НеДенежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления Неденежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/updateMonetaryBank")
    @Produces("application/json; charset=UTF-8")
    public Response updateMonetaryBank(@QueryParam("id") int id,
                                       @QueryParam("name") String name,
                                       @QueryParam("summ") int summ,
                                       @QueryParam("currencyName") String currencyName,
                                       @QueryParam("nameBank") String nameBank,
                                       @QueryParam("numberAcc") int numberAcc) {
        try {
            Bank bank = new Bank(summ, currencyName, nameBank, numberAcc);
            Monetary monetary = new Monetary(id, name, bank);
            for (Active active : activeMap) {
                if (active instanceof Monetary && active.getId() == id) {
                    int index = activeMap.indexOf(active);
                    activeMap.add(index, monetary);
                }
            }
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка добавления денежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/updateMonetaryCash")
    @Produces("application/json; charset=UTF-8")
    public Response updateMonetaryCash(@QueryParam("id") int id,
                                       @QueryParam("name") String name,
                                       @QueryParam("summ") int summ,
                                       @QueryParam("currencyName") String currencyName,
                                       @QueryParam("nameCash") String nameCash,
                                       @QueryParam("numberAcc") int numberAcc) {
        try {
            Cash cash = new Cash(summ, currencyName, nameCash);
            Monetary monetary = new Monetary(id, name, cash);
            for (Active active : activeMap) {
                if (active instanceof Monetary && active.getId() == id) {
                    int index = activeMap.indexOf(active);
                    activeMap.add(index, monetary);
                }
            }
            return Response.ok(stringJson.toJson("Денежный актив добавлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка обновления денежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/updateNonMonetary")
    @Produces("application/json; charset=UTF-8")
    public Response updateNonMonetary(@QueryParam("id") int id,
                                      @QueryParam("name") String name,
                                      @QueryParam("primary") int primary,
                                      @QueryParam("residual") int residual,
                                      @QueryParam("valuation") int valuation,
                                      @QueryParam("measurement") String measurement) {
        try {
            Map<String, Object> map;
            JSONObject jsonObj = new JSONObject(measurement);
            map = jsonObj.toMap();
            NonMonetary nonMonetary = new NonMonetary(id, name, primary, residual, valuation, map);
            for (Active active : activeMap) {
                if (active instanceof NonMonetary && active.getId() == id) {
                    int index = activeMap.indexOf(active);
                    activeMap.add(index, nonMonetary);
                }
            }
            return Response.ok(stringJson.toJson("НеДенежный актив обновлен успешно")).build();
        } catch (Exception e) {
            log.error("Ошибка обновления Неденежного актива", e);
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}

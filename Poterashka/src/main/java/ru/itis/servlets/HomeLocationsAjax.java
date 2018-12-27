package ru.itis.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.forms.BuildForm;
import ru.itis.models.Building;
import ru.itis.models.Location;
import ru.itis.repositories.ClientRepositoryImpl;
import ru.itis.repositories.LocationRepositoryImpl;
import ru.itis.repositories.OrderRepositoryImpl;
import ru.itis.services.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/HomeLocationsAjax")
public class HomeLocationsAjax extends HttpServlet {

    LocationServices locationServices;



    @Override
    @SneakyThrows
    public void init() throws ServletException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        Class.forName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/poterashka");
        locationServices = new LocationServicesImpl(new LocationRepositoryImpl(dataSource));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String buildId = req.getParameter("bild");
        String locId = req.getParameter("loc");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        if(locId != null){
            resp.getWriter().write((new ObjectMapper()).writeValueAsString(locationServices.getLocationsInLocation(Integer.parseInt(locId))));
        }else {
            Building b = locationServices.getBuilding(Integer.parseInt(buildId));
            resp.getWriter().write((new ObjectMapper()).writeValueAsString((new ArrayList<BuildForm>()).add(BuildForm.builder()
                    .building(b)
                    .locations(locationServices.getLocationsInBuilding(b.getId()))
                    .build())));
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Building> buildings = locationServices.getBuildings();
        List<BuildForm> buildForms = new ArrayList<>();
        for (Building b :
                buildings) {
            buildForms.add(BuildForm.builder()
                    .building(b)
                    .locations(locationServices.getLocationsInBuilding(b.getId()))
                    .build());
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.getWriter().write((new ObjectMapper()).writeValueAsString(buildForms));
    }



}

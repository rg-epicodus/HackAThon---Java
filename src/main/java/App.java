import dao.Sql2oMemberDao;
import dao.Sql2oTeamDao;
import models.Member;
import models.Team;

import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:h2:~/todolist.db;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        Sql2oTeamDao teamDao = new Sql2oTeamDao(sql2o);
        Sql2oMemberDao memberDao = new Sql2oMemberDao(sql2o);

        // show all teams (root route)
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Member> member = memberDao.getAll();
            List<Team> team = teamDao.getAll();
            model.put("member", member);
            model.put("team", team);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show about page
        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        // show new team route
        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model, "teamForm.hbs");
        }, new HandlebarsTemplateEngine());

        // create a new team
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            Team newTeam = new Team(teamName, teamDescription);
            teamDao.add(newTeam);
            model.put("team", newTeam);
            response.redirect("/");
            return null;
        });
//
//
//
//        //get: show an individual post
//        get("/teams/:id", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            int idOfTeamToFind = Integer.parseInt(req.params("id"));
//            Team team = Team.findById(idOfTeamToFind);
//            model.put("team", team);
//            return new ModelAndView(model, "teamInformation.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //get: show a form to update a post
//        get("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            Team editTeam = Team.findById(Integer.parseInt(req.params("id")));
//            model.put("editTeam", editTeam);
//            return new ModelAndView(model, "teamForm.hbs");
//        }, new HandlebarsTemplateEngine());
//
//        //post: process a form to update a post
//        post("/teams/:id/update", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            String newTeamName = req.queryParams("teamName");
//            Team editTeam = Team.findById(Integer.parseInt(req.params("id")));
//            editTeam.updateTeamName(newTeamName);
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());














    }
}

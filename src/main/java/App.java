import models.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        //get: delete all posts
        get("/teams/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Team.clearAllTeams();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show about page
        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        // show new team route
        get("/teams/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "teamForm.hbs");
        }, new HandlebarsTemplateEngine());



        // create a new team
        post("/teams/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            String teamName = request.queryParams("teamName");
            String teamDescription = request.queryParams("teamDescription");
            String teamMember = request.queryParams("teamMember");
            Team newTeam = new Team(teamName, teamDescription);
            newTeam.addTeamMember(teamMember);
            model.put("team", newTeam);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        // show all teams (root route)
        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            ArrayList<Team> teams = Team.getAllTeams();
            model.put("teams", teams);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show an individual post
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Team team = Team.findById(Integer.parseInt(req.params("id")));
            model.put("teams", team);
            return new ModelAndView(model, "teamInformation.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a post
        get("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Team editTeam = Team.findById(Integer.parseInt(req.params("id")));
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "teamForm.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process a form to update a post
        post("/teams/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String newTeamName = req.queryParams("teamName");
            Team editTeam = Team.findById(Integer.parseInt(req.params("id")));
            editTeam.updateTeamName(newTeamName);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

//        //get: delete an individual post
//        get("/posts/:id/delete", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            Team deleteTeam = Team.findById(Integer.parseInt(req.params("id")));
//            deleteTeam.deleteTeam();
//            return new ModelAndView(model, "success.hbs");
//        }, new HandlebarsTemplateEngine());











    }
}

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

        //get: show about page
        get("/about", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show an individual post
        get("/teams/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int findTeamByID = Integer.parseInt(req.params("id")); //pull id - must match route segment
            Team team = Team.findById(findTeamByID); //use it to find post
            model.put("team", team);//add it to model for template to display
            return new ModelAndView(model, "teamInformation.hbs"); //individual post page.
        }, new HandlebarsTemplateEngine());















    }
}

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
        get("/", (req, res) -> {
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

        // show an enter team route
        get("/team/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model, "teamForm.hbs");
        }, new HandlebarsTemplateEngine());

        // show team information
        get("/team/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model, "teamInformation.hbs");
        }, new HandlebarsTemplateEngine());


        // create a new team
        post("/team/new", (req, res) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String teamName = req.queryParams("teamName");
            String teamDescription = req.queryParams("teamDescription");
            Team newTeam = new Team(teamName, teamDescription);
            teamDao.add(newTeam);
            model.put("team", newTeam);
            res.redirect("/");
            return null;
        });

        //get: to enter a new member
        get("/member/new",(request, response) -> {
            Map<String, Object> model = new HashMap<>();
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model,"member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: process new member
        post("/member/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String teamName = request.queryParams("teamName");
            String memberName = request.queryParams("memberName");
            int teamId = teamDao.findByName(teamName);
            Member member = new Member(memberName, teamId);
            memberDao.add(member);
            List<Member> members = memberDao.getAll();
            model.put("member", members);
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model,"index.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to update teams
        get("/team/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Team> teams = teamDao.getAll();
            int idOfNewTeam = Integer.parseInt(request.params("id"));
            model.put("teams", teams);
            Team editTeam = teamDao.findById(idOfNewTeam);
            model.put("editTeam", editTeam);
            return new ModelAndView(model, "teamForm.hbs");
        }, new HandlebarsTemplateEngine());

        //post: show the form to update a team
        post("/team/:id/update", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("teamName");
            String description = req.queryParams("teamDescription");
            int idOfTeamToUpdate = Integer.parseInt(req.params("id"));
            teamDao.update(name, description, idOfTeamToUpdate);
            List<Member> member = memberDao.getAll();
            model.put("member", member);
            List<Team> team = teamDao.getAll();
            model.put("teams", team);
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show member details
        get("/team/:teamId/member/:memberId", (request, response) ->{
            Map<String, Object> model = new HashMap<>();
            Member member = memberDao.findById(Integer.parseInt(request.params("memberId")));
            model.put("member", member);
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            return new ModelAndView(model, "member-detail.hbs");
        }, new HandlebarsTemplateEngine());

        //get a form to update a member
        get("/team/:teamId/member/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            Member editMember = memberDao.findById(Integer.parseInt(request.params("id")));
            List<Team> team = teamDao.getAll();
            model.put("team", team);
            model.put("editMember", editMember);
            return new ModelAndView(model, "member-form.hbs");
        }, new HandlebarsTemplateEngine());

        //post: update member
        post("/team/:teamId/member/:id/update", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String team = request.queryParams("teamName");
            String memberName = request.queryParams("memberName");
            int teamId = teamDao.findByName(team);
            int id = Integer.parseInt(request.params("id"));
            memberDao.update(memberName, id, teamId);
            List<Member> member = memberDao.getAll();
            model.put("member", member);
            response.redirect("/");
        return null;
        });






    }
}

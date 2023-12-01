package backend.service.make_cookbook;

import backend.entity.Cookbook;

public interface MakeCookbookDataAccessInterface {
    /*Check if the cookbook title already exist*/
    boolean existByTitle(String identifier);
    /*saves the cookbook*/
    void addCookbook(Cookbook cookbook);
}

package edu.stanford.cs108.bunnyworld.Classes.MainClasses;

public class Page {
    private String pageName;
    private String defaultPageName = "Page";

    public Page() {
        this.pageName = defaultPageName;
    }

    public String getPageName() {
        return pageName;
    }

    public void renamePage(String newName) {
        this.pageName = newName;
    }
}

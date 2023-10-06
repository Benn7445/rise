package me.quartz.rise.files;

public class FileManager {

    private final CustomFile mapsFile;

    public FileManager() {
        this.mapsFile = new CustomFile("maps");
    }

    public CustomFile getMapsFile() {
        return mapsFile;
    }
}



import java.util.*;

interface FileSystemElement {
    void display();
    void add(FileSystemElement element);
    void remove(FileSystemElement element);
}

class File implements FileSystemElement {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("File: " + name);
    }

    @Override
    public void add(FileSystemElement element) {
        throw new UnsupportedOperationException("Cannot add to a file");
    }

    @Override
    public void remove(FileSystemElement element) {
        throw new UnsupportedOperationException("Cannot remove from a file");
    }
}

class Directory implements FileSystemElement {
    private String name;
    private List<FileSystemElement> elements;

    public Directory(String name) {
        this.name = name;
        this.elements = new ArrayList<>();
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemElement element : elements) {
            element.display();
        }
    }

    @Override
    public void add(FileSystemElement element) {
        elements.add(element);
    }

    @Override
    public void remove(FileSystemElement element) {
        elements.remove(element);
    }
}

public class FileSystem {
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        Directory dir1 = new Directory("Dir1");
        Directory dir2 = new Directory("Dir2");
        File file1 = new File("File1");
        File file2 = new File("File2");

        root.add(dir1);
        root.add(dir2);
        dir1.add(file1);
        dir2.add(file2);

        root.display();
    }
}
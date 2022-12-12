import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;

public class FluentFile implements Serializable, Comparable<File> {
    private final File file;

    public FluentFile(String path) {
        this.file = new File(path);
    }

    public String appendPath(String path) {
        return new File(this.getAbsolutePath(), path).getAbsolutePath();
    }

    public boolean canExecute() {
        return this.file.canExecute();
    }

    public boolean canRead() {
        return this.file.canRead();
    }

    public boolean canWrite() {
        return this.file.canWrite();
    }

    public FluentFile child(String path) {
        if(!this.exists()) {
            this.mkdirs();
        }
        if(this.isFile()) {
            return new FluentFile(this.getParentFile().appendPath(path));
        }

        return new FluentFile(this.appendPath(path));
    }

    public int compareTo(File pathname) {
        return this.file.compareTo(pathname);
    }

    public boolean createNewFile() throws IOException {
        return this.file.createNewFile();
    }

    public boolean delete() {
        return this.file.delete();
    }

    public FluentFile deleteOnExit() {
        this.file.deleteOnExit();
        return this;
    }

    public boolean equals(Object obj) {
        if(obj.getClass() == FluentFile.class) {
            return this.file.equals(((FluentFile) obj).file);
        }
        if(obj.getClass() == File.class) {
            return this.file.equals(obj);
        }

        return false;
    }

    public boolean exists() {
        return this.file.exists();
    }

    public static FluentFile fromFile(File file) {
        return new FluentFile(file.getAbsolutePath());
    }

    public File getAbsoluteFile() {
        return this.file.getAbsoluteFile();
    }

    public String getAbsolutePath() {
        return this.file.getAbsolutePath();
    }

    public File getCanonicalFile() throws IOException {
        return this.file.getCanonicalFile();
    }

    public String getCanonicalPath() throws IOException {
        return this.file.getCanonicalPath();
    }

    public long getFreeSpace() {
        return this.file.getFreeSpace();
    }

    public String getName() {
        return this.file.getName();
    }

    public String getName(boolean withExtension) {
        String name = this.file.getName();

        if(withExtension) {
            return name;
        }

        int stop = name.lastIndexOf(".");
        return name.substring(0, stop);
    }

    public String getParent() {
        return this.file.getParent();
    }

    public FluentFile getParentFile() {
        return FluentFile.fromFile(this.file.getParentFile());
    }

    public String getPath() {
        return this.file.getPath();
    }

    public long getTotalSpace() {
        return this.file.getTotalSpace();
    }

    public long getUsableSpace() {
        return this.file.getUsableSpace();
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    public boolean isAbsolute() {
        return this.file.isAbsolute();
    }

    public boolean isDirectory() {
        return this.file.isDirectory();
    }

    public boolean isFile() {
        return this.file.isFile();
    }

    public boolean isHidden() {
        return this.file.isHidden();
    }

    public long lastModified() {
        return this.file.lastModified();
    }

    public long length() {
        return this.file.length();
    }

    public String[] list() {
        return this.file.list();
    }

    public String[] list(FilenameFilter filter) {
        return this.file.list(filter);
    }

    public FluentFile[] listFiles() {
        return this.listFiles(File::isFile);
    }

    public FluentFile[] listFiles(FileFilter filter) {
        File[] files = this.file.listFiles(filter);
        if(files == null) {
            return new FluentFile[0];
        }

        FluentFile[] result = new FluentFile[files.length];
        for(int i = 0; i < files.length; i++) {
            result[i] = FluentFile.fromFile(files[i]);
        }

        return result;
    }

    public boolean mkdir() {
        return this.file.mkdir();
    }

    public boolean mkdirs() {
        return this.file.mkdirs();
    }

    public String readFile() throws FileNotFoundException {
        ArrayList<String> sb = new ArrayList<>();
        if(!this.isFile()) {
            return "";
        }

        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        reader.lines().forEach(sb::add);
        return String.join("\n", sb);
    }

    public boolean renameTo(File dest) {
        return this.file.renameTo(dest);
    }

    public boolean setExecutable(boolean executable) {
        return this.file.setExecutable(executable);
    }

    public boolean setExecutable(boolean executable, boolean ownerOnly) {
        return this.file.setExecutable(executable, ownerOnly);
    }

    public boolean setLastModified(long time) {
        return this.file.setLastModified(time);
    }

    public boolean setReadable(boolean readable) {
        return this.file.setReadable(readable);
    }

    public boolean setReadable(boolean readable, boolean ownerOnly) {
        return this.file.setReadable(readable, ownerOnly);
    }

    public boolean setReadOnly() {
        return this.file.setReadOnly();
    }

    public boolean setWritable(boolean writable) {
        return this.file.setWritable(writable);
    }

    public boolean setWritable(boolean writable, boolean ownerOnly) {
        return this.file.setWritable(writable, ownerOnly);
    }

    public Path toPath() {
        return this.file.toPath();
    }

    public String toString() {
        return this.file.toString();
    }

    public URI toURI() {
        return this.file.toURI();
    }
}

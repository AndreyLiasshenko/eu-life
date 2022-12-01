package org.EuLife.dao.Entities;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Image() {    }

    private String title;

    private String fileName;
    private int width;
    private int height;

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "USER_ID")
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Lob
    @Column(name = "BINARY_IMAGE", nullable = false)
    private byte[] image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image other = (Image) o;
        if (!title.equals(other.title)) return false;
        if (!fileName.equals(other.fileName)) return false;
        if (width != other.width) return false;
        if (height != other.height) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

package computer_builder.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "computer_parts")
public class ComputerPart {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Part name is mandatory")
    private String title;

    @PositiveOrZero (message = "This value can't be negative")
    private int quantity;


    private boolean required;


    public ComputerPart() {
    }

    public ComputerPart(String title, int quantity, boolean required) {
        this.title = title;
        this.quantity = quantity;
        this.required = required;
    }


    public long getId() {
        return id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }


    @Override
    public String toString() {
        return "ComputerPart{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", quantity=" + quantity +
                ", required=" + required +
                '}';
    }
}

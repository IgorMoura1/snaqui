import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String cpf;
    private LocalDateTime birthDate;
    private List<Address> addresses;
    private List<Order> orderHistory;
    private LoyaltyProgram loyaltyProgram;
    private Double walletBalance;
    private List<CreditCard> creditCards;
    private List<Review> reviews;
    private NotificationSettings notificationSettings;

    public void register() {
        // Implementation for customer registration
    }

    public void updateProfile() {
        // Implementation for updating customer profile
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addCreditCard(CreditCard creditCard) {
        this.creditCards.add(creditCard);
    }

    public void rechargeWallet(Double amount) {
        this.walletBalance += amount;
    }
}
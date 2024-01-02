import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Service {
    private String serviceName;
    private boolean enabled;

    public Service(String serviceName) {
        this.serviceName = serviceName;
        this.enabled = false;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }
}

class Bill {
    private double balance;

    public Bill() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void addToBalance(double amount) {
        balance += amount;
    }

    public boolean deductFromBalance(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class Subscriber {
    private String phoneNumber;
    private Bill bill;
    private List<Service> services;

    public Subscriber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.bill = new Bill();
        this.services = new ArrayList<>();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Bill getBill() {
        return bill;
    }

    public List<Service> getServices() {
        return services;
    }

    public void addService(Service service) {
        services.add(service);
    }

    public void removeService(Service service) {
        services.remove(service);
    }

    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
}

class Administrator {
    public void temporarilyDisconnectSubscriber(Subscriber subscriber) {
        System.out.println("Абонент " + subscriber.getPhoneNumber() + " временно отключен.");
    }

    public void changePhoneNumber(Subscriber subscriber, String newPhoneNumber) {
        subscriber.changePhoneNumber(newPhoneNumber);
        System.out.println("Номер абонента изменен на " + newPhoneNumber);
    }

    public void disconnectSubscriberForNonPayment(Subscriber subscriber) {
        System.out.println("Абонент " + subscriber.getPhoneNumber() + " отключен за неуплату.");
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите начальный номер абонента: ");
        String phoneNumber = scanner.nextLine();
        Subscriber subscriber = new Subscriber(phoneNumber);
        Service voiceService = new Service("Связь");
        Service internetService = new Service("Интернет");
        subscriber.addService(voiceService);
        subscriber.addService(internetService);
        Administrator administrator = new Administrator();
        System.out.print("Хотите изменить номер абонента? (да/нет): ");
        String changeNumberResponse = scanner.next().toLowerCase();
        if (changeNumberResponse.equals("да")) {
            System.out.print("Введите новый номер абонента: ");
            String newPhoneNumber = scanner.next();
            administrator.changePhoneNumber(subscriber, newPhoneNumber);
        }
        while (true) {
            System.out.print("Хотите включить или выключить услугу? (включить/выключить/нет): ");
            String serviceAction = scanner.next().toLowerCase();
            if (serviceAction.equals("нет")) {
                break;
            }

            System.out.print("Введите название услуги (Связь/Интернет): ");
            String serviceName = scanner.next().toLowerCase();

            for (Service service : subscriber.getServices()) {
                if (service.getServiceName().equalsIgnoreCase(serviceName)) {
                    if (serviceAction.equals("включить")) {
                        service.enable();
                        System.out.println("Услуга " + service.getServiceName() + " включена.");
                    } else if (serviceAction.equals("выключить")) {
                        service.disable();
                        System.out.println("Услуга " + service.getServiceName() + " выключена.");
                    }
                }
            }
        }
        System.out.println("Текущие услуги абонента:");
        for (Service service : subscriber.getServices()) {
            System.out.println(service.getServiceName() + ": " + (service.isEnabled() ? "Включена" : "Выключена"));
        }
        if (subscriber.getBill().getBalance() < 0) {
            administrator.disconnectSubscriberForNonPayment(subscriber);
        }
        System.out.println("Обновленные услуги абонента:");
        for (Service service : subscriber.getServices()) {
            System.out.println(service.getServiceName() + ": " + (service.isEnabled() ? "Включена" : "Выключена"));
        }
    }
}

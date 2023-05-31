package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InformationCard {
    private String number;
    private String month;
    private String year;
    private String name;
    private String cvc;
}

package pl.bdygasinski.businesslogic.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AccountDataForUpdate(String firstName, String lastName, String country, String city, String street,
                                   Integer houseNumber, String postalCode, String locale, LocalDate dateOfBirth) {

}

package pl.akademiaqa.dto;

import com.microsoft.playwright.Locator;
import lombok.Builder;

@Builder
public record ProductDTO(Locator thumbnail, String name, double price) {
}

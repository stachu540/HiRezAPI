package hirezapi.enums;

import lombok.Getter;

/**
 * Language.
 * @author <a href="damian@stachuofficial.pl">Damian Staszewski</a>
 * @since 1.8
 */
@Getter
public enum Language {
  English(1),
  German(2),
  French(3),
  Spanish(7),
  Latin_Spanish(9),
  Portuguese(10),
  Russian(11),
  Polish(12),
  Turkish(13);

  private final int id;
  private final String name;

  Language(final int id) {
    this.name = name().replace("_", " ");
    this.id = id;
  }
}

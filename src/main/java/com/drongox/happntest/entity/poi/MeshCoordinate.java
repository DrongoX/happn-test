package com.drongox.happntest.entity.poi;

import static java.text.MessageFormat.format;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MeshCoordinate
{
  private static final BigDecimal LOWER_LATITUDE_BOUND = BigDecimal.valueOf(-90);
  private static final BigDecimal UPPER_LATITUDE_BOUND = BigDecimal.valueOf(90);
  private static final BigDecimal LOWER_LONGITUDE_BOUND = BigDecimal.valueOf(-180);
  private static final BigDecimal UPPER_LONGITUDE_BOUND = BigDecimal.valueOf(180);

  private final BigDecimal latitude;
  private final BigDecimal longitude;


  public static MeshCoordinate of(BigDecimal latitude, BigDecimal longitude)
  {
    verifyCoordinateBounds(latitude, LOWER_LATITUDE_BOUND, UPPER_LATITUDE_BOUND);
    verifyCoordinateBounds(longitude, LOWER_LONGITUDE_BOUND, UPPER_LONGITUDE_BOUND);
    return new MeshCoordinate(latitude, longitude);
  }


  private static void verifyCoordinateBounds(BigDecimal coordinate,
                                             BigDecimal lowerBound,
                                             BigDecimal upperBound)
  {
    if (coordinate.compareTo(lowerBound) < 0 || coordinate.compareTo(upperBound) > 0) {
      throw new IllegalArgumentException(
          format("Coordinate problem: {0} should be in [{1}, {2}]", coordinate, lowerBound, upperBound));
    }
  }
}

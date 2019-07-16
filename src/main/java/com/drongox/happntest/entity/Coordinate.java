package com.drongox.happntest.entity;

import static java.text.MessageFormat.format;

import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Coordinate
{
  private static final BigDecimal LOWER_LATITUDE_BOUND = BigDecimal.valueOf(-90);
  private static final BigDecimal UPPER_LATITUDE_BOUND = BigDecimal.valueOf(90);
  private static final BigDecimal LOWER_LONGITUDE_BOUND = BigDecimal.valueOf(-180);
  private static final BigDecimal UPPER_LONGITUDE_BOUND = BigDecimal.valueOf(180);

  private final BigDecimal latitude;
  private final BigDecimal longitude;


  public static Coordinate of(BigDecimal latitude, BigDecimal longitude)
  {
    verifyCoordinateBounds(latitude, LOWER_LATITUDE_BOUND, UPPER_LATITUDE_BOUND);
    verifyCoordinateBounds(longitude, LOWER_LONGITUDE_BOUND, UPPER_LONGITUDE_BOUND);
    return new Coordinate(latitude, longitude);
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


  public boolean isLongitudeUpperBound()
  {
    return longitude.compareTo(UPPER_LONGITUDE_BOUND) == 0;
  }


  public boolean isLatitudeUpperBound()
  {
    return latitude.compareTo(UPPER_LATITUDE_BOUND) == 0;
  }

//
//  @Override
//  public int hashCode()
//  {
//    return Objects.hash(latitude.doubleValue(), longitude.doubleValue());
//  }
//
//
//  @Override
//  public boolean equals(Object o)
//  {
//    if (o == this) return true;
//    if (!(o instanceof PoiCoordinate)) return false;
//    return this.latitude.compareTo(((PoiCoordinate) o).latitude) == 0 &&
//           this.longitude.compareTo(((PoiCoordinate) o).longitude) == 0;
//  }
}

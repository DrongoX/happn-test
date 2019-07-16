package com.drongox.happntest.entity.mesh;

import static java.math.BigDecimal.valueOf;
import static java.text.MessageFormat.format;

import com.drongox.happntest.entity.Coordinate;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@Getter
@ToString
public final class MeshArea
{
  private static final double MESH_GRANULARITY = 0.5;

  private static final BigDecimal UPPER_LATITUDE_BOUND = valueOf(90);
  private static final BigDecimal UPPER_LONGITUDE_BOUND = valueOf(180);

  private final BigDecimal lowerLatitude;
  private final BigDecimal upperLatitude;
  private final BigDecimal leftLongitude;
  private final BigDecimal rightLongitude;


  public MeshArea(Coordinate coordinate)
  {
    Coordinate normalizedCoordinate = normalizeToLowerLeft(coordinate);

    if (normalizedCoordinate.isLongitudeUpperBound()) {
      rightLongitude = normalizedCoordinate.getLongitude();
      leftLongitude = normalizedCoordinate.getLongitude().subtract(valueOf(MESH_GRANULARITY));
    }
    else {
      leftLongitude = normalizedCoordinate.getLongitude();
      rightLongitude = normalizedCoordinate.getLongitude().add(valueOf(MESH_GRANULARITY));
    }

    if (normalizedCoordinate.isLatitudeUpperBound()) {
      upperLatitude = normalizedCoordinate.getLatitude();
      lowerLatitude = normalizedCoordinate.getLatitude().subtract(valueOf(MESH_GRANULARITY));
    }
    else {
      lowerLatitude = normalizedCoordinate.getLatitude();
      upperLatitude = normalizedCoordinate.getLatitude().add(valueOf(MESH_GRANULARITY));
    }
  }


  private static Coordinate normalizeToLowerLeft(Coordinate coordinate)
  {
    return Coordinate.of(normalizeCoordinate(coordinate.getLatitude()),
                         normalizeCoordinate(coordinate.getLongitude()));
  }


  private static BigDecimal normalizeCoordinate(BigDecimal coordinate)
  {
    return valueOf(MESH_GRANULARITY *
                   Math.floor(coordinate.multiply(valueOf(1 / MESH_GRANULARITY)).doubleValue()));
  }


  @Override
  public int hashCode()
  {
    return Objects.hash(lowerLatitude.doubleValue(), leftLongitude.doubleValue());
  }


  @Override
  public boolean equals(Object o)
  {
    if (o == this) {
      return true;
    }
    if (!(o instanceof MeshArea)) {
      return false;
    }
    return this.leftLongitude.compareTo(((MeshArea) o).leftLongitude) == 0 &&
           this.lowerLatitude.compareTo(((MeshArea) o).lowerLatitude) == 0;
  }
}

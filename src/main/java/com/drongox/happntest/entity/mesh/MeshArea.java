package com.drongox.happntest.entity.mesh;

import static java.math.BigDecimal.valueOf;

import com.drongox.happntest.entity.GeoCoordinate;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
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


  public MeshArea(GeoCoordinate geoCoordinate)
  {
    GeoCoordinate normalizedGeoCoordinate = normalizeToLowerLeft(geoCoordinate);

    if (normalizedGeoCoordinate.isLongitudeUpperBound()) {
      rightLongitude = normalizedGeoCoordinate.getLongitude();
      leftLongitude = normalizedGeoCoordinate.getLongitude().subtract(valueOf(MESH_GRANULARITY));
    }
    else {
      leftLongitude = normalizedGeoCoordinate.getLongitude();
      rightLongitude = normalizedGeoCoordinate.getLongitude().add(valueOf(MESH_GRANULARITY));
    }

    if (normalizedGeoCoordinate.isLatitudeUpperBound()) {
      upperLatitude = normalizedGeoCoordinate.getLatitude();
      lowerLatitude = normalizedGeoCoordinate.getLatitude().subtract(valueOf(MESH_GRANULARITY));
    }
    else {
      lowerLatitude = normalizedGeoCoordinate.getLatitude();
      upperLatitude = normalizedGeoCoordinate.getLatitude().add(valueOf(MESH_GRANULARITY));
    }
  }


  private static GeoCoordinate normalizeToLowerLeft(GeoCoordinate geoCoordinate)
  {
    return GeoCoordinate.of(normalizeCoordinate(geoCoordinate.getLatitude()),
                            normalizeCoordinate(geoCoordinate.getLongitude()));
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

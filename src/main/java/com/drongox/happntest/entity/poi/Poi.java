package com.drongox.happntest.entity.poi;

import com.drongox.happntest.entity.GeoCoordinate;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public final class Poi
{
  private final String id;
  private final GeoCoordinate geoCoordinate;


  @Override
  public int hashCode()
  {
    return Objects.hash(id, geoCoordinate);
  }


  @Override
  public boolean equals(Object o)
  {
    if (o == this) return true;
    if (!(o instanceof Poi)) return false;
    return Objects.equals(id, ((Poi) o).id) &&
           Objects.equals(geoCoordinate, ((Poi) o).geoCoordinate);
  }
}

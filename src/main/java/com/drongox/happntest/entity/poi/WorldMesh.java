package com.drongox.happntest.entity.poi;

import static java.math.BigDecimal.valueOf;

import java.math.BigDecimal;
import java.util.*;

public final class WorldMesh
{
  private static final double MESH_GRANULARITY = 0.5;
  private final Map<MeshCoordinate, List<Poi>> poiMesh = new HashMap<>();


  public List<Poi> getPoiOn(MeshCoordinate coordinate)
  {
    return poiMesh.getOrDefault(coordinate, List.of());
  }


  public void addPoi(Poi poi)
  {
    MeshCoordinate normalizedCoordinate = normalize(poi.getCoordinate());
    poiMesh.computeIfAbsent(normalizedCoordinate, coord -> new ArrayList<>())
           .add(poi);
  }


  private static MeshCoordinate normalize(MeshCoordinate coordinate)
  {
    return MeshCoordinate.of(normalizeCoordinate(coordinate.getLatitude()),
                             normalizeCoordinate(coordinate.getLongitude()));
  }


  private static BigDecimal normalizeCoordinate(BigDecimal coordinate)
  {
    return valueOf(MESH_GRANULARITY *
        Math.floor(coordinate.multiply(valueOf(1 / MESH_GRANULARITY)).doubleValue()));
  }
}

package com.drongox.happntest.entity.poi;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public final class Poi
{
  private final String id;
  private final MeshCoordinate coordinate;
}

package com.drongox.happntest.entity.poi;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.Value;


@Value
@RequiredArgsConstructor(staticName = "of")
public class MeshCoordinate
{
  private final BigDecimal latitude;
  private final BigDecimal longitude;
}

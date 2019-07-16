package com.drongox.happntest.entity;


import static java.math.BigDecimal.valueOf;

import org.junit.Test;

public class CoordinateTest
{
  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_if_latidude_less_than_minus_90()
  {
    Coordinate.of(valueOf(-90.01), valueOf(3.0));
  }


  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_if_latidude_more_than_90()
  {
    Coordinate.of(valueOf(90.001), valueOf(3.0));
  }


  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_if_longitude_less_than_minus_180()
  {
    Coordinate.of(valueOf(0.1), valueOf(-180.001));
  }


  @Test(expected = IllegalArgumentException.class)
  public void should_throw_IAE_if_longitude_more_than_180()
  {
    Coordinate.of(valueOf(0.1), valueOf(180.001));
  }


  @Test(expected = Test.None.class)
  public void should_throw_no_exception_when_latitude_and_longitude_in_bounds()
  {
    Coordinate.of(valueOf(-90.0), valueOf(180.00));
  }
}
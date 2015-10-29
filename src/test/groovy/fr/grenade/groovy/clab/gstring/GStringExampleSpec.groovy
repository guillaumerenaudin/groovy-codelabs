package fr.grenade.groovy.clab.gstring

import spock.lang.*


/**
 * @author grenaudin
 *
 */
class GStringExampleSpec extends Specification {

  @Shared
  SubObject subObject

  @Shared
  def subObjects


  def setupSpec(){
    subObject = new SubObject(position : 1, description : "myDescription")
    subObjects = []
    subObjects << new SubObject(position : 1, description : "myDescription1")
    subObjects << new SubObject(position : 2, description : "myDescription2")
    subObjects << new SubObject(position : 3, description : "myDescription3")
  }

  @Unroll("should get the correct value of a property from its path relatively to the given root object (path is #path)")
  def "should get the correct value of a property from its path relatively to the given root object" () {

    given: "Definition of the root object"
    RootObject object = new RootObject(myString : "aValue", value: 2.0, myChild: subObject, myChildren: subObjects, )


    expect: "calling the GStringExample.retrieveAnObjectValueFromAPath give us the correct property value"
    result == GStringExample.retrieveAnObjectValueFromAPath(object, path)

    where:
    path                        | result
    "myString"                  | "aValue"
    "value"                     | 2.0
    "myChild"                   | subObject
    "myChild.position"          | 1
    "myChildren[2]"             | subObjects[2]
    "myChildren[2].position"    | 3
  }

  @Unroll("should get the correct value of each property of a SubObject from its path relatively to the given root object (path is #path)")
  def "should get the correct value of each property of a SubObject from its path relatively to the given root object" () {

    given: "Definition of the root object"
    RootObject object = new RootObject(myString : "aValue", value: 2.0, myChild: subObject, myChildren: subObjects)


    expect: "calling the GStringExample.retrieveAllFieldValuesFromAnObjectPath give us the correct property value"
    result == GStringExample.retrieveAllFieldValuesFromAnObjectPath(object, path)

    where:
    path                        | result
    "myChildren[0]"             | ["description" : "myDescription1", position : 1]
    "myChild"                   | ["description" : "myDescription", position : 1]
  }
}

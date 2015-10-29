package fr.grenade.groovy.clab.gstring

class GStringExample {

  static def retrieveAnObjectValueFromAPath(RootObject object,String pathVariable){
    def result = null

    if(object != null){
      result = Eval.x(object, "x.${pathVariable}")
    }

    return result
  }

  static def retrieveAllFieldValuesFromAnObjectPath(RootObject object,String pathVariable){
    def result = [:]

    def foundObject = GStringExample.retrieveAnObjectValueFromAPath(object, pathVariable)

    if(object != null){
      result << [
        "position" :
        foundObject.position
      ]
      result << [
        "description" :
        foundObject.description
      ]
    }

    return result
  }
}

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form class="formulaire" method="post" action="${pageContext.request.contextPath}/enregistrer">
 <fieldset>
  <legend>
   <c:choose>
    <c:when test="{not empty livre.id}">
     Edition livre
    </c:when>
    <c:otherwise>
     Ajout livre
    </c:otherwise>
   </c:choose>
  </legend>
  <c:choose>
   <c:when test="${not empty livre.id}">
    <input type="hidden"
 name="id" value="${livre.id}"/>
 <input type ="hidden" name="edition" value="true"/>
</c:when>
<c:otherwise>
 <label for="title">Code</label>
 <input type="text" name="id" id="id" value="${livre.id}" />
 <input type="hidden" name="edition" value="false">
</c:otherwise>
  </c:choose>
  <div>
   <label for="title">Titre</label>
   <input type="text" name="titre" id="titre" value="${livre.title}"/>
  </div>
  <div>
   <label for="description">Description</label>
   <textarea name="description" id="description" cols="60" rows="10">${livre.description}</textarea>
  </div>
  <div>
   <label for="auteur">Auteur</label>
   <input  name="auteur" id="auteur" value="${livre.auteur}"/>
  </div>
  <div>
   <label for="prix">Prix</label>
   <input name="prix" id="prix" value="${livre.prix}"/>
  </div>
 </fieldset>
 <div class="button-row">
  <a href="${pageContext.request.contextPath}/listerlivres">Cancel</a> 
  <input type="submit" value="Soumettre"/>

 </div>
</form>
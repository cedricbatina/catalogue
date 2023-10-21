<%@ taglib uri="httmp://java.sun.com/jsp/jstl/core" prefix="c"%>
<a href="${pageContext.request.contextPath}/editer">Ajouter un livre</a>
<table class="listing">
 <tr>
  <th>
   Code<span class="sortLink"> [ <a href="${pageContext.request.contextPath}/listerlivres?ordre=asc&champ=id">asc</a> | <a href="${pageContext.request.contextPath}/listerlivres?ordre=desc&champ=id">desc</a>]
    </span>
  </th>
  <th>
   Titre
   <span class="sortLink"> [
    <a href="${pageContext.request.contextPah}/listerlivres?ordre=asc&champ=titre">asc</a> |
     <a href="${pageContext.request.contextPath}/listerlivres?ordre=desc&champ=titre">desc</a> | 
    ]

   </span>
  </th>
  <th>
   Description
  </th>
  <th>Auteur <span class="sortLink"> 
   [
   <a href="${pageContext.request.contextPath}/listerlivres?ordre=asc&champ=auteur">asc</a> |
   <a href="${pageContext.request.contextPath}/listerlivres?ordre=asc&champ=auteur">desc</a> 

   ]

  </span>

  </th>
  <th>Prix
   <span class="sortLink">
    [
    <a href="${pageContext.request.contextPath}/listerlivres?ordre=asc&champ=prix">asc</a> |
    <a href="${pageContext.request.contextPath}/listerlivres?ordre=asc&champ=prix">desc</a>
 

    ]
   </span> 

  </th>
  <th>
   Action
  </th>
 </tr>
<c:forEach var ="livre" items="${livres}">
<tr>
 <td>
  <a href="${pageContext.request.contextPath}/editer?id=${livre.id}" >${livre.id}></a>
 </td>
 <td>${livre.id} </td>
 <td>${livre.titre} </td>
 <td>${livre.description} </td>
 <td>${livre.auteur} </td>
 <td>${livre.prix} </td>
 <td> <a href="${pageContext.requestcontextPath}/supprimer?id=${livre.id}">Supprimer</a></td>
</tr></c:forEach>"
</table>
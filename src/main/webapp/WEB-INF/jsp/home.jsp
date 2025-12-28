<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Taxidermie Prestige | Accueil</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <h1>Taxidermie Prestige</h1>
    <nav>
        <a href="/">Accueil</a>
        <a href="/catalog">Catalogue</a>
        <a href="${pageContext.request.contextPath}/cart">
            Panier (${cart.itemCount})
        </a>
        <a href="/login">Connexion</a>
    </nav>
</header>

<c:if test="${not empty successMessage}">
    <div class="success">
            ${successMessage}
    </div>
</c:if>

<div class="hero">
    <div class="container">
        <h2>L'art de l'immortalité naturelle</h2>
        <p>Découvrez notre collection unique de pièces naturalisées avec passion.</p>
    </div>
</div>

<div class="container">
    <h2 style="margin-bottom: 30px; border-bottom: 2px solid #ddd; padding-bottom: 10px;">Nos Dernières Pièces</h2>

    <div class="search-bar-container" style="background: white; padding: 20px; border-radius: 8px; margin-bottom: 30px; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">
        <form action="${pageContext.request.contextPath}/" method="get" style="display: flex; gap: 15px; flex-wrap: wrap;">

            <input type="text" name="search" value="${currentSearch}" placeholder="Rechercher un spécimen..."
                   style="flex-grow: 1; padding: 10px; border: 1px solid #ddd; border-radius: 4px;">

            <select name="categoryId" style="padding: 10px; border: 1px solid #ddd; border-radius: 4px;">
                <option value="">Toutes les catégories</option>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.id}" ${currentCategoryId == cat.id ? 'selected' : ''}>
                            ${cat.nameFr} </option>
                </c:forEach>
            </select>

            <button type="submit" class="btn">Rechercher</button>

            <a href="${pageContext.request.contextPath}/" class="btn" style="background-color: #7f8c8d;">Réinitialiser</a>
        </form>
    </div>


    <div class="product-grid">
        <c:forEach items="${products}" var="p">
            <article class="product-card">
                <img src="${pageContext.request.contextPath}/images/${p.imageUrl}"
                     alt="${p.name}"
                     class="product-image"
                     onerror="this.src='https://placehold.co/400x300?text=Pas+d+image'">

                <div class="product-info">
                    <h3>${p.name}</h3>
                    <p class="desc">${p.description}</p>
                    <div class="price">${p.price} €</div>
                    <div class="product-info">
                        <form action="${pageContext.request.contextPath}/cart/add" method="post">
                            <input type="hidden" name="productId" value="${p.id}">
                            <input type="hidden" name="quantity" value="1">
                            <button type="submit" class="btn">Ajouter au panier</button>
                        </form>
                    </div>
                </div>
            </article>
        </c:forEach>
    </div>

    <c:if test="${productPage.totalPages > 1}">
        <div class="pagination" style="display: flex; justify-content: center; gap: 10px; margin-top: 30px;">

            <c:if test="${productPage.hasPrevious()}">
                <a href="?page=${productPage.number - 1}&search=${currentSearch}&categoryId=${currentCategoryId}" class="btn" style="background: #fff; color: #333; border: 1px solid #ddd;">&laquo; Précédent</a>
            </c:if>

            <c:forEach begin="0" end="${productPage.totalPages - 1}" var="i">
                <a href="?page=${i}&search=${currentSearch}&categoryId=${currentCategoryId}"
                   class="btn"
                   style="${productPage.number == i ? 'background-color: var(--primary-color); color: white;' : 'background: #fff; color: #333; border: 1px solid #ddd;'}">
                        ${i + 1}
                </a>
            </c:forEach>

            <c:if test="${productPage.hasNext()}">
                <a href="?page=${productPage.number + 1}&search=${currentSearch}&categoryId=${currentCategoryId}" class="btn" style="background: #fff; color: #333; border: 1px solid #ddd;">Suivant &raquo;</a>
            </c:if>
        </div>
    </c:if>

</div>

<footer>
    <p>&copy; 2026 Taxidermie Prestige. Fait avec Spring Boot & JSP.</p>
</footer>

</body>
</html>
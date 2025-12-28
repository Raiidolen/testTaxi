<%--
  Created by IntelliJ IDEA.
  User: zerix
  Date: 28-12-25
  Time: 19:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Votre Panier - Taxidermie Prestige</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <h1>Taxidermie Prestige</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/">Continuer les achats</a>
    </nav>
</header>

<div class="container" style="margin-top: 40px;">
    <h2>Votre Panier</h2>

    <c:if test="${empty cart.items}">
        <p>Votre panier est vide. <a href="${pageContext.request.contextPath}/">Retourner au catalogue</a>.</p>
    </c:if>

    <c:if test="${not empty cart.items}">
        <table style="width: 100%; border-collapse: collapse; margin-bottom: 20px;">
            <thead>
            <tr style="background: #eee; text-align: left;">
                <th style="padding: 10px;">Produit</th>
                <th style="padding: 10px;">Prix</th>
                <th style="padding: 10px;">Quantité</th>
                <th style="padding: 10px;">Total</th>
                <th style="padding: 10px;">Action</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cart.items}" var="item">
                <tr style="border-bottom: 1px solid #ddd;">
                    <td style="padding: 10px;">
                        <div style="display: flex; align-items: center;">
                            <img src="${pageContext.request.contextPath}/images/${item.product.imageUrl}"
                                 style="width: 50px; height: 50px; object-fit: cover; margin-right: 10px;"
                                 onerror="this.src='https://placehold.co/50'">
                            <strong>${item.product.name}</strong>
                        </div>
                    </td>
                    <td style="padding: 10px;">${item.product.price} €</td>
                    <td style="padding: 10px;">${item.quantity}</td>
                    <td style="padding: 10px;">
                        <fmt:formatNumber value="${item.total}" type="currency" currencySymbol="€"/>
                    </td>
                    <td style="padding: 10px;">
                        <a href="${pageContext.request.contextPath}/cart/remove/${item.product.id}"
                           class="btn" style="background-color: #e74c3c; font-size: 0.8rem;">X</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div style="text-align: right; font-size: 1.5rem; font-weight: bold; margin-bottom: 20px;">
            Total : <fmt:formatNumber value="${cart.totalAmount}" type="currency" currencySymbol="€"/>
        </div>

        <div style="text-align: right;">
            <a href="${pageContext.request.contextPath}/checkout" class="btn" style="padding: 15px 30px;">Passer la commande</a>
        </div>
    </c:if>
</div>

</body>
</html>

# =============================================
# Stage 1: Build con Node
# =============================================
FROM node:20-alpine AS build

WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .
RUN npm run build

# =============================================
# Stage 2: Runtime con Nginx
# =============================================
FROM nginx:alpine

# Copia la config de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copia los archivos estáticos compilados
COPY --from=build /app/dist /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
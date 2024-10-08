package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.configs.security.UserDetailsImpl;
import com.abhaempire.complifybook.dtos.CategoryResponse;
import com.abhaempire.complifybook.dtos.PublicServiceResponse;
import com.abhaempire.complifybook.dtos.ServiceDetailResponse;
import com.abhaempire.complifybook.dtos.ServiceResponse;
import com.abhaempire.complifybook.dtos.SubCategoryResponse;
import com.abhaempire.complifybook.dtos.SubscriptionRequest;
import com.abhaempire.complifybook.dtos.SubscriptionResponse;
import com.abhaempire.complifybook.enums.ResultTypeEnum;
import com.abhaempire.complifybook.enums.Role;
import com.abhaempire.complifybook.enums.StatusTypeEnum;
import com.abhaempire.complifybook.models.Category;
import com.abhaempire.complifybook.models.Service;
import com.abhaempire.complifybook.models.ServiceDetails;
import com.abhaempire.complifybook.models.SubCategory;
import com.abhaempire.complifybook.models.Subscriber;
import com.abhaempire.complifybook.models.TagService;
import com.abhaempire.complifybook.models.User;
import com.abhaempire.complifybook.models.UserRole;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.CollectionUtils;

public class ObjectMapper {
    public static User mapToSaveUser(String firstName, String lastName, String email, String password) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .isEmailVerified(false)
                .createdBy("0")
                .status(StatusTypeEnum.INACTIVE)
                .build();
        user.setUserRoles(mapToUserRole(user));
        return user;
    }

    private static List<UserRole> mapToUserRole(User user) {
        return Collections.singletonList(
                UserRole.builder()
                        .user(user)
                        .role(Role.ROLE_ADMIN)
                        .createdBy("0")
                        .build()
        );
    }

    public static void mapToSaveCategory(Category category) {
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        category.setCreatedBy(Utils.getUserId(loggedInUser));
        category.setStatus(StatusTypeEnum.ACTIVE);
    }

    public static List<CategoryResponse> mapToCategoryResponse(List<Category> categories) {
        if (CollectionUtils.isEmpty(categories)) {
            return new ArrayList<>();
        }
        return categories.stream()
                .map(ObjectMapper::mapToCategory)
                .collect(Collectors.toList());
    }

    public static CategoryResponse mapToCategory(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .createdAt(Utils.convertToLocalDate(category.getCreatedAt()))
                .name(category.getName())
                .status(category.getStatus())
                .build();
    }

    public static void mapToSaveSubCategory(SubCategory subCategory) {
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        subCategory.setStatus(StatusTypeEnum.ACTIVE);
        subCategory.setCreatedBy(Utils.getUserId(loggedInUser));
    }

    public static List<SubCategoryResponse> mapToSubCategoryResponse(List<SubCategory> subCategoryList) {
        if (CollectionUtils.isEmpty(subCategoryList)) {
            return new ArrayList<>();
        }
        return subCategoryList.stream()
                .map(ObjectMapper::mapToSubCategory).toList();
    }

    private static SubCategoryResponse mapToSubCategory(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .iconName(subCategory.getIcon())
                .categoryName(subCategory.getCategory().getName())
                .subCategoryName(subCategory.getName())
                .status(subCategory.getStatus())
                .createdAt(Utils.convertToLocalDate(subCategory.getCreatedAt()))
                .build();
    }

    public static List<ServiceResponse> mapToServiceResponse(List<Service> services) {
        if (CollectionUtils.isEmpty(services)) {
            return new ArrayList<>();
        }
        return services.stream()
                .map(ObjectMapper::mapToServiceResponse)
                .toList();
    }

    public static ServiceResponse mapToServiceResponse(Service service) {
        return ServiceResponse.builder()
                .id(service.getId())
                .slug(service.getSlug())
                .subCategoryName(service.getSubCategory().getName())
                .serviceName(service.getName())
                .rating(service.getRating())
                .status(service.getStatus())
                .createdAt(Utils.convertToLocalDate(service.getCreatedAt()))
                .build();
    }
    public static PublicServiceResponse mapToPublicServiceResponse(Service service) {
        return PublicServiceResponse.builder()
                .slug(service.getSlug())
                .name(service.getName())
                .summary(service.getSummary())
                .rating(service.getRating())
                .ratingCount(service.getRatingCount())
                .build();
    }

    public static void mapToSaveService(
            Service service, List<Integer> tagServices) {
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        service.setStatus(StatusTypeEnum.ACTIVE);
        service.setCreatedBy(Utils.getUserId(loggedInUser));
        service.setTagServices(mapToTagServices(service, tagServices));
    }

    private static List<TagService> mapToTagServices(Service service, List<Integer> tagServices) {
        if (CollectionUtils.isEmpty(tagServices)) {
            return new ArrayList<>();
        }
        return tagServices.stream()
                .map(id -> TagService.builder()
                        .service(service)
                        .tagServiceId(id)
                        .status(StatusTypeEnum.ACTIVE)
                        .build())
                .toList();
    }

    public static void mapToUpdateService(
            Service service, List<TagService> tagServiceList, List<Integer> tagService) {

        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        service.setUpdatedBy(Utils.getUserId(loggedInUser));

        if (CollectionUtils.isEmpty(tagService)) {
            if (!CollectionUtils.isEmpty(tagServiceList)) {
                tagServiceList.forEach(ts -> ts.setStatus(StatusTypeEnum.DELETED));
            }
            return ;
        }
        List<Integer> commonElements = tagServiceList.stream()
                .map(TagService::getTagServiceId)
                .filter(tagService::contains)
                .toList();
        tagServiceList.forEach(tagS -> {
            if (!commonElements.contains(tagS.getTagServiceId())) {
                tagS.setStatus(StatusTypeEnum.DELETED);
            }
        });
        List<TagService> tagServices = tagService.stream()
                .filter(tgId -> !commonElements.contains(tgId))
                .map(tgId -> mapToTagService(service, tgId)).toList();

        service.setTagServices(tagServices);
    }

    private static TagService mapToTagService(Service service, Integer taggedServiceId) {
        return TagService.builder()
                .status(StatusTypeEnum.ACTIVE)
                .service(service)
                .tagServiceId(taggedServiceId)
                .build();
    }

    public static List<ServiceDetailResponse> mapToServiceDetailsResponse(
            List<ServiceDetails> serviceDetailsList) {
        if (CollectionUtils.isEmpty(serviceDetailsList)){
            return new ArrayList<>();
        }
        return serviceDetailsList.stream()
                .map(ObjectMapper::mapToServiceDetailResponse)
                .toList();
    }

    private static ServiceDetailResponse mapToServiceDetailResponse(ServiceDetails serviceDetails) {
        return ServiceDetailResponse.builder()
                .id(serviceDetails.getId())
                .tabName(serviceDetails.getTabName())
                .tabOrder(serviceDetails.getTabOrder())
                .status(serviceDetails.getStatus())
                .createdAt(Utils.convertToLocalDate(serviceDetails.getCreatedAt()))
                .build();
    }

    public static void mapToSaveServiceDetails(ServiceDetails serviceDetails, Service service) {
        UserDetailsImpl loggedInUser = UserDetailsUtil.getLoggedInUser();
        serviceDetails.setCreatedBy(Utils.getUserId(loggedInUser));
        serviceDetails.setStatus(StatusTypeEnum.ACTIVE);
        serviceDetails.setService(service);
    }

  public static Subscriber maptoSubscriber(SubscriptionRequest subscriptionRequest) {
        return Subscriber.builder()
            .email(subscriptionRequest.getEmail())
            .url(Optional.ofNullable(subscriptionRequest.getUrl()).orElse("/"))
            .createdBy(Utils.getUserId(UserDetailsUtil.getLoggedInUser()))
            .status(StatusTypeEnum.ACTIVE)
            .build();
  }

    public static SubscriptionResponse mapToPassSubscriptionResponse() {
        return SubscriptionResponse.builder()
            .result(ResultTypeEnum.PASS)
            .message(AppConstant.SUBSCRIPTION_SUCCESS)
            .build();
    }
    public static SubscriptionResponse mapToExistSubscriptionResponse() {
        return SubscriptionResponse.builder()
            .result(ResultTypeEnum.EXIST)
            .message(AppConstant.ALREADY_SUBSCRIBED)
            .build();
    }
}

(function($) {
  'use strict';
  var host = window.location.origin;
//  all category
if ($('#serviceCategory').length) {
    function fetchServiceCategoryData() {
      $.ajax({
        url: host + '/api/v1/category/fetchAll',
        type: 'GET',
        dataType: 'json',
        success: function(response) {
          var categoryDataSet = response.map(function(item) {
            return [item.id, item.name, item.status, item.createdAt];
          });
          var serviceCategory = $('#serviceCategory').DataTable({
            data: categoryDataSet,
            columns: [
              { title: "ID", visible: false },
              { title: "Name" },
              { title: "Status" },
              { title: "Created On" },
              {
                title: "Actions",
                orderable: false, // Disable sorting for the Action column
                data: null,
                render: function(data, type, row) {
                  return `
                    <div class="action">
                      <a href="/api/v1/category/edit/${row[0]}">Edit</a>
                      <a href="javascript:void(0);" onclick="deleteCategory(${row[0]})">Delete</a>
                    </div>
                  `;
                }
              }
            ],
            destroy: true
          });
        },
        error: function(error) {
          console.error("Error fetching service categories:", error);
        }
      });
    }
    fetchServiceCategoryData();
  }
  // ALL SUB-CATEGORY
  if ($('#serviceSubCategory').length) {
      function fetchServiceSubCategoryData() {
        $.ajax({
          url: host + '/api/v1/sub-category/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var subCategoryDataSet = response.map(function(item) {
              return [
                  item.id,
                  item.iconName,
                  item.categoryName,
                  item.subCategoryName,
                  item.status,
                  item.createdAt
              ];
            });
            var serviceSubCategory = $('#serviceSubCategory').DataTable({
              data: subCategoryDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Icon" },
                { title: "Category" },
                { title: "Sub Category" },
                { title: "Status" },
                { title: "Created On" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/sub-category/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching service sub-categories:", error);
          }
        });
      }
      fetchServiceSubCategoryData();
    }
    //ALL SERVICES
    if ($('#serviceTable').length) {
          function fetchServiceData() {
            $.ajax({
              url: host + '/api/v1/service/fetchAll',
              type: 'GET',
              dataType: 'json',
              success: function(response) {
                var serviceDataSet = response.map(function(item) {
                  return [
                      item.id,
                      item.subCategoryName,
                      item.serviceName,
                      item.rating,
                      item.status,
                      item.createdAt
                  ];
                });
                var serviceTable = $('#serviceTable').DataTable({
                  data: serviceDataSet,
                  columns: [
                    { title: "ID", visible: false },
                    { title: "Sub Category" },
                    { title: "Service Name" },
                    { title: "Rating" },
                    { title: "Status" },
                    { title: "Created On" },
                    {
                      title: "Actions",
                      orderable: false,
                      data: null,
                      render: function(data, type, row) {
                        return `
                          <div class="action">
                            <a href="/api/v1/service/${row[0]}/details">Details</a>
                            <a href="/api/v1/service/edit/${row[0]}">Edit</a>
                            <a href="javascript:void(0);" onclick="deleteService(${row[0]})">Delete</a>
                          </div>
                        `;
                      }
                    }
                  ],
                  destroy: true
                });
              },
              error: function(error) {
                console.error("Error fetching service :", error);
              }
            });
          }
          fetchServiceData();
        }

        //ALL SERVICE DETAILS
        if ($('#serviceDetailsTable').length) {
          let serviceId = window.location.pathname.split('/')[4];
          $("#serviceDetailNew").attr("href", "/api/v1/service/" + serviceId + "/details/new");
          function fetchServiceDetailsData() {
            $.ajax({
              url: host + '/api/v1/service/'+ serviceId +'/details/fetchAll',
              type: 'GET',
              dataType: 'json',
              success: function(response) {
                var serviceDetailsDataSet = response.map(function(item) {
                console.log(item);
                  return [
                      item.id,
                      item.tabName,
                      item.status,
                      item.createdAt
                  ];
                });
                var serviceDetailsTable = $('#serviceDetailsTable').DataTable({
                  data: serviceDetailsDataSet,
                  columns: [
                    { title: "ID", visible: false },
                    { title: "Tab Name" },
                    { title: "Status" },
                    { title: "Created On" },
                    {
                      title: "Actions",
                      orderable: false,
                      data: null,
                      render: function(data, type, row) {
                        return `
                          <div class="action">
                            <a href="/api/v1/service/details/edit/${row[0]}">Edit</a>
                            <a href="javascript:void(0);" onclick="deleteServiceDetails(${row[0]})">Delete</a>
                          </div>
                        `;
                      }
                    }
                  ],
                  destroy: true
                });
              },
              error: function(error) {
                console.error("Error fetching service details data :", error);
              }
            });
          }
          fetchServiceDetailsData();
        }
if ($('#serviceEnquiry').length) {
      function fetchServiceEnquiryData() {
        $.ajax({
          url: host + '/api/v1/enquiry/service/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var serviceEnquiryDataSet = response.map(function(item) {
              return [
                  item.id,
                  item.enquiryDate,
                  item.name,
                  item.email,
                  item.mobile,
                  item.urlPath,
                  item.status
              ];
            });
            var serviceEnquiry = $('#serviceEnquiry').DataTable({
              data: serviceEnquiryDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Date" },
                { title: "Name" },
                { title: "Email" },
                { title: "Mobile" },
                { title: "Url Path" },
                { title: "Status" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/enquiry/service/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching service enquiry data:", error);
          }
        });
      }
      fetchServiceEnquiryData();
    }
if ($('#partnershipEnquiry').length) {
      function fetchPartnershipEnquiryData() {
        $.ajax({
          url: host + '/api/v1/enquiry/partnership/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var partnershipEnquiryDataSet = response.map(function(item) {
              return [
                  item.id,
                  item.enquiryDate,
                  item.name,
                  item.email,
                  item.mobile,
                  item.occupation,
                  item.status
              ];
            });
            var serviceEnquiry = $('#partnershipEnquiry').DataTable({
              data: partnershipEnquiryDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Date" },
                { title: "Name" },
                { title: "Email" },
                { title: "Mobile" },
                { title: "Occupation" },
                { title: "Status" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/enquiry/partnership/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching partnership enquiry data:", error);
          }
        });
      }
      fetchPartnershipEnquiryData();
    }
if ($('#contactUsEnquiry').length) {
      function fetchContactUsEnquiryData() {
        $.ajax({
          url: host + '/api/v1/enquiry/contact-us/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var contactUsEnquiryDataSet = response.map(function(item) {
              return [
                  item.id,
                  item.enquiryDate,
                  item.name,
                  item.email,
                  item.mobile,
                  item.subject,
                  item.status
              ];
            });
            var contactUsEnquiry = $('#contactUsEnquiry').DataTable({
              data: contactUsEnquiryDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Date" },
                { title: "Name" },
                { title: "Email" },
                { title: "Mobile" },
                { title: "Subject" },
                { title: "Status" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/enquiry/contact-us/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching contact-us data:", error);
          }
        });
      }
      fetchContactUsEnquiryData();
    }
    if ($('#subscriberTable').length) {
          function fetchSubscriberData() {
            $.ajax({
              url: host + '/api/v1/subscriber/fetchAll',
              type: 'GET',
              dataType: 'json',
              success: function(response) {
                var subscriberDataSet = response.map(function(item) {
                  return [
                      item.id,
                      item.email,
                      item.urlPath,
                      item.status,
                      item.subscribedOn,
                  ];
                });
                var subscriberTable = $('#subscriberTable').DataTable({
                  data: subscriberDataSet,
                  columns: [
                    { title: "ID", visible: false },
                    { title: "Email" },
                    { title: "Url Path" },
                    { title: "Status" },
                    { title: "Subscribed On" },
                    {
                      title: "Actions",
                      orderable: false, // Disable sorting for the Action column
                      data: null,
                      render: function(data, type, row) {
                        return `
                          <div class="action">
                            <a href="/api/v1/subscriber/edit/${row[0]}">Edit</a>
                            <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                          </div>
                        `;
                      }
                    }
                  ],
                  destroy: true
                });
              },
              error: function(error) {
                console.error("Error fetching subscriber data:", error);
              }
            });
          }
          fetchSubscriberData();
        }
    if ($('#testimonialDataTable').length) {
      function fetchTestimonialData() {
        $.ajax({
          url: host + '/api/v1/testimonial/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var testimonialDataSet = response.map(function(item) {
              return [
                  item.id,
                  `<a href="${item.imagePath}" class="tbl-img" target="_blank">
                    <img src="${item.imagePath}" alt="Photo" />
                  </a>`,
                  item.reviewerName,
                  item.position,
                  item.rating,
                  item.status,
                  item.createdAt,
              ];
            });
            var testimonialDataTable = $('#testimonialDataTable').DataTable({
              data: testimonialDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Photo" },
                { title: "Reviewer Name" },
                { title: "Position" },
                { title: "Rating" },
                { title: "Status" },
                { title: "Posted On" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/testimonial/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching testimonial data:", error);
          }
        });
      }
      fetchTestimonialData();
    }
    if ($('#invoiceDataTable').length) {
      function fetchInvoiceData() {
        $.ajax({
          url: host + '/api/v1/invoice/fetchAll',
          type: 'GET',
          dataType: 'json',
          success: function(response) {
            var invoiceDataSet = response.map(function(item) {
              return [
                  item.id,
                  item.orderNumber,
                  item.customerName,
                  item.invoiceAmount,
                  item.taxAmount,
                  item.dueAmount,
                  item.date,
                  item.paymentStatus
              ];
            });
            var invoiceDataTable = $('#invoiceDataTable').DataTable({
              data: invoiceDataSet,
              columns: [
                { title: "ID", visible: false },
                { title: "Order No." },
                { title: "Customer Name" },
                { title: "Total Amount" },
                { title: "Tax Amount" },
                { title: "Due Amount" },
                { title: "Created On" },
                { title: "Payment Status" },
                {
                  title: "Actions",
                  orderable: false, // Disable sorting for the Action column
                  data: null,
                  render: function(data, type, row) {
                    return `
                      <div class="action">
                        <a href="/api/v1/invoice/edit/${row[0]}">Edit</a>
                        <a href="javascript:void(0);" onclick="deleteSubCategory(${row[0]})">Delete</a>
                      </div>
                    `;
                  }
                }
              ],
              destroy: true
            });
          },
          error: function(error) {
            console.error("Error fetching invoice data:", error);
          }
        });
      }
      fetchInvoiceData();
    }
})(jQuery);

function deleteCategory(categoryId){
    alert(categoryId);
}
function deleteSubCategory(subCategoryId){
    alert(subCategoryId);
}
function deleteService(serviceId){
    alert(serviceId);
}
function deleteServiceDetails(serviceId){
    alert(serviceId);
}
function slugging(content, targetId){
	$("#"+targetId).val(content. toLowerCase(). replace(/ /g,'-'). replace(/[^\w-]+/g,''));
}